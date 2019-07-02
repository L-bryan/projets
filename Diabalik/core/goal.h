#ifndef GOAL_H
#define GOAL_H

#include "team.h"
#include "coordinate.h"
#include <vector>

class Goal
{
    Team team;
    std::vector<Coordinate> go;
    const int GOAL_SIZE = 7;
public:

    /**
     * create a Goal for a specified team where the Team is the parameter
     */
    Goal(Team);

    /**
     * @brief isInside check if a coordinate is in the goal
     * @return true if the coordiante is in the goal else the function retrun false
     */
    bool isInside(Coordinate);
};

#endif // GOAL_H
