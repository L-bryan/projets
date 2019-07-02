#ifndef BOARD_HPP
#define BOARD_HPP

#include<vector>
#include"square.hpp"

/**
 * @brief The Board class
 */
class Board
{
private:
    int width;
    int height;
    int unminedSquare;
    std::vector<std::vector<Square>> squares;
public:

    /**
     * Construct a Board object
     * with a default width & height
     * @brief Board object
     */
    Board();

    /**
     * Construct a Board object
     * with a custom width & height
     * @brief Board object
     * @param width of the board
     * @param height of the board
     */
    Board(int width,int height);

    /**
     * @brief isUnminedSquareZero Check if
     * the number of unminedSquare attribute is set to zero
     * @return true if unminedSquare == 0 else return false
     */
    bool isUnminedSquareZero();

    /**
     * @brief countNeighbourMinedNumber count
     * the number of mined Neighbour
     * @return the number of mined Neighbour
     */
    int countNeighbourMinedNumber(Square square);

    /**
     * @brief reveal is a recursive method
     * who reveal the square of a coordinate
     * @param coordinate the coordinate of the square
     */
    void reveal(Coordinate coordinate);
};

#endif // BOARD_HPP
