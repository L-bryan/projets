#include "goal.h"

Goal::Goal(Team t) : team(t)
{
    if(t == Team::BLUE){
        for(int i = 0 ; i<GOAL_SIZE;i++){
            go.push_back(Coordinate(0,i));
        }
    }else{
        for(int i = 0; i<GOAL_SIZE;i++){
            go.push_back(Coordinate(GOAL_SIZE-1,i));
        }
    }
}

bool Goal::isInside(Coordinate pos)
{
    bool isInside = false;
    for(Coordinate c : go){
        isInside = (c == pos);
        if(isInside){
            break;
        }
    }
    return isInside;
}
