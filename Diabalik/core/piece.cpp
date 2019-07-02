#include "piece.h"

Piece::Piece() : team(Team::NONE)
{}

Piece::Piece(Team t, bool b, Coordinate c) : team(t), ball(b), pos(c)
{}

Coordinate Piece::getCoordinate()
{
    return pos;
}

bool Piece::haveBall()
{
    return ball;
}

Team Piece::getTeam()
{
    return team;
}

void Piece::move(Coordinate c)
{
    pos = c;
}

void Piece::setball(bool b)
{
    ball = b;
}
