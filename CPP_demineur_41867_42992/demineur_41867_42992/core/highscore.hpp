#ifndef HIGHSCORE_H
#define HIGHSCORE_H

#include<vector>
#include"playerscore.hpp"

/**
 * @brief The HighScore class
 */
class HighScore
{
private:
   std::vector<PlayerScore> score;
public:

   /**
     * Construct a HighScore with a empty list
     * the list is a vector
     * @brief HighScore object
     */
    HighScore();

    /**
     * @brief addScore add a PlayerScore structure
     * to the list
     */
    void addScore();
};

#endif // HIGHSCORE_H
