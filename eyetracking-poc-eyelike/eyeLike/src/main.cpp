#include <opencv2/objdetect/objdetect.hpp>
#include <opencv2/highgui/highgui.hpp>
#include <opencv2/imgproc/imgproc.hpp>

#include <iostream>
#include <queue>
#include <utility>
#include <stdio.h>
#include <math.h>

#include "constants.h"
#include "findEyeCenter.h"
#include "findEyeCorner.h"

/** Function Headers */
void detectAndDisplay(cv::Mat &frame );

/** Global variables */
//-- Note, either copy these two files from opencv/data/haarscascades to your current folder, or change these locations
cv::String face_cascade_name = "../../eyeLike/res/haarcascade_frontalface_alt.xml";
cv::CascadeClassifier face_cascade;
std::string main_window_name = "Capture - Face detection";
std::string face_window_name = "Capture - Face";
//cv::RNG rng(12345);
cv::Mat debugImage;

cv::Rect face = cv::Rect(0, 0, 0, 0);

/**
 * @function main
 */
int main( int argc, const char** argv ) {
  cv::Mat frame;

  // Load the cascades
  if( !face_cascade.load( face_cascade_name ) ){ printf("--(!)Error loading face cascade, please change face_cascade_name in source code.\n"); return -1; };

  cv::namedWindow(main_window_name,CV_WINDOW_NORMAL);
  cv::moveWindow(main_window_name, 400, 100);
  cv::namedWindow(face_window_name,CV_WINDOW_NORMAL);
  cv::moveWindow(face_window_name, 10, 100);
  cv::namedWindow("Right Eye",CV_WINDOW_NORMAL);
  cv::moveWindow("Right Eye", 10, 600);
  cv::namedWindow("Left Eye",CV_WINDOW_NORMAL);
  cv::moveWindow("Left Eye", 10, 800);

  // 0 comme argument pour la premier camera trouvée, ou le chemin du device (/dev/video2 par exemple)
  cv::VideoCapture capture("/home/cabal/Webcam/roll_eyes_p.webm");
  if( capture.isOpened() ) {
    while( true ) {
      capture.read(frame);
      // mirror it
      cv::flip(frame, frame, 1);
      frame.copyTo(debugImage);

      // Apply the classifier to the frame
      if( !frame.empty() ) {
        detectAndDisplay( frame );
      }
      else {
        printf(" --(!) No captured frame -- Break!");
        break;
      }

      imshow(main_window_name,debugImage);

      int c = cv::waitKey(10);
      if( (char)c == 'c' ) { break; }

    }
  }

  return 0;
}

/**
 * @brief Finds eye regions based on the face rectangle.
 * @param face the face.
 * @return a pair where the first element is the left eye region, and the second element is the right eye region.
 */
std::pair<cv::Rect, cv::Rect> findEyeRegions(cv::Rect& face) {
    //-- Find eye regions and draw them
    int eye_region_width = static_cast<int>(face.width * (kEyePercentWidth/100.0));
    int eye_region_height = static_cast<int>(face.width * (kEyePercentHeight/100.0));
    int eye_region_top = static_cast<int>(face.height * (kEyePercentTop/100.0));
    int eye_region_side = static_cast<int>(face.width * (kEyePercentSide/100.0));

    cv::Rect leftEyeRegion(eye_region_side, eye_region_top,eye_region_width,eye_region_height);
    cv::Rect rightEyeRegion(face.width - eye_region_width - eye_region_side,
                            eye_region_top,eye_region_width,eye_region_height);
    return std::pair<cv::Rect, cv::Rect>(leftEyeRegion, rightEyeRegion);
}

std::pair<cv::Point,cv::Point> findPupils(cv::Mat& faceROI, std::pair<cv::Rect, cv::Rect>& eyeRegions) {
    cv::Rect leftEyeRegion = eyeRegions.first;
    cv::Rect rightEyeRegion = eyeRegions.second;

    //-- Find pupils
    cv::Point leftPupil = findEyeCenter(faceROI,leftEyeRegion,"Left Eye");
    cv::Point rightPupil = findEyeCenter(faceROI,rightEyeRegion,"Right Eye");

    // change eye centers to face coordinates
    rightPupil.x += rightEyeRegion.x;
    rightPupil.y += rightEyeRegion.y;
    leftPupil.x += leftEyeRegion.x;
    leftPupil.y += leftEyeRegion.y;

    return std::pair<cv::Point,cv::Point>(leftPupil, rightPupil);
}

/**
 * @brief Draw a circle representing the pupils on the given face.
 * @param face the face.
 * @param pupils a pair where the first element is the left pupil, and the second element is the right pupil.
 */
void drawPupils(cv::Mat& face, std::pair<cv::Point, cv::Point>& pupils) {
    circle(face, pupils.first, 3, 1234);
    circle(face, pupils.second, 3, 1234);
}

/**
 * @brief Detects the eyes and the pupils, and display them on the output window.
 * @param frame the image matrix to use.
 */
void detectAndDisplay( cv::Mat& frame ) {
  std::vector<cv::Rect> faces;

  std::vector<cv::Mat> rgbChannels(3);
  cv::split(frame, rgbChannels);
  cv::Mat frame_gray = rgbChannels[2];

  //-- Detect faces
  face_cascade.detectMultiScale( frame_gray, faces, 1.1, 2, 0|CV_HAAR_SCALE_IMAGE|CV_HAAR_FIND_BIGGEST_OBJECT, cv::Size(150, 150) );

  //-- Show what you got

  if (faces.size() > 0) {
      int maxOffset = 2;
      if (abs(faces[0].width - face.width) > maxOffset || abs(faces[0].height - face.height) > maxOffset)
        face = faces[0];

    rectangle(debugImage, face, 1234);

    cv::Mat faceROI = frame_gray(face);
    std::pair<cv::Rect, cv::Rect> eyeRegions = findEyeRegions(face);
    std::pair<cv::Point, cv::Point> pupils = findPupils(faceROI, eyeRegions);
    drawPupils(faceROI, pupils);

    imshow(face_window_name, faceROI);
  }
}


