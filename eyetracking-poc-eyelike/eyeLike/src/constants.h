#ifndef CONSTANTS_H
#define CONSTANTS_H

// Debugging
const bool kPlotVectorField = false;

// Size constants
const int kEyePercentTop = 33;
const int kEyePercentSide = 15;
const int kEyePercentHeight = 13;
const int kEyePercentWidth = 25;

// Preprocessing
const bool kSmoothFaceImage = false;
const float kSmoothFaceFactor = 0.005;

// Algorithm Parameters
const int kFastEyeWidth = 50;
const int kWeightBlurSize = 5;
const bool kEnableWeight = true;
const float kWeightDivisor = 1.0;
const double kGradientThreshold = 50.0;

// Postprocessing
const bool kEnablePostProcess = true;
const float kPostProcessThreshold = 0.97;

#endif
