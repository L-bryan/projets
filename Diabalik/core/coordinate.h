#ifndef COORDINATE_H
#define COORDINATE_H


class Coordinate
{
    int x;
    int y;
public:

    /**
     * @brief Coordinate the default constructor of Coordiante
     */
    Coordinate();

    /**
     * @brief Coordinate Create a object Coordiante with a sepcified line and
     * a specified column
     */
    Coordinate(int,int);

    /**
     * @brief operator == check if two Coordiante are same.
     * @return true if x and y values of coordiante A is equals of a Coordiante B
     * else the function return false
     */
    bool operator ==(Coordinate);

    /**
     * @brief getX get the X or line of a coordinate
     * @return a int
     */
    int getX();

    /**
     * @brief getY get the Y or column of a coordinate
     * @return a int
     */
    int getY();
};

#endif // COORDINATE_H
