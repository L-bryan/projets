#ifndef PLAYER_H
#define PLAYER_H

#include "team.h"
#include "coordinate.h"

class Piece
{
    Team team;
    bool ball;
    Coordinate pos;
public:

    /**
     * @brief Piece the Default constructor of Piece
     * ball is false and Team is NONE and pos is (-1,-1)
     */
    Piece();

    /**
     * @brief Piece Create a Piece object with a Team,
     * a bool if the piece have the ball and a coordiante
     */
    Piece(Team, bool, Coordinate);

    /**
     * @brief getCoordinate get the coordinate of the piece
     * @return a Coordiante
     */
    Coordinate getCoordinate();

    /**
     * @brief haveBall get the value of ball attribut
     * @return true if piece have the ball else function return false
     */
    bool haveBall();

    /**
     * @brief getTeam get the team of the piece
     * @return a enum Team
     */
    Team getTeam();

    /**
     * @brief move move the piece to a coordiante
     */
    void move(Coordinate);

    /**
     * @brief setball set the ball to true or false
     */
    void setball(bool);
};

#endif // PLAYER_H
