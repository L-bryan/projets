#include "coordinate.h"

Coordinate::Coordinate() : x(-1), y(-1)
{}
Coordinate::Coordinate(int x , int y) : x(x), y(y)
{}
bool Coordinate::operator ==(Coordinate c){
    return x == c.getX() && y == c.getY();
}

Coordinate::getX(){return x;}
Coordinate::getY(){return y;}
