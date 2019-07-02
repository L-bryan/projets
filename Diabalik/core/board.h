#ifndef BOARD_H
#define BOARD_H

#include <vector>
#include "piece.h"
#include "coordinate.h"

class Board
{
    std::vector<std::vector<Piece>> board;
    const int WIDTH = 7;
    const int HIGHT = 7;
public:
    /**
     * @brief Board defaulte constructor of board
     */
    Board();

    /**
     * @brief getBoard get the Board
     * @return a vector of vector of Piece
     */
    std::vector<std::vector<Piece>> getBoard();

    /**
     * @brief isPosFree check if a Coordiante in the board is free
     * @return true if the position is free else function return false
     */
    bool isPosFree(Coordinate);

    /**
     * @brief isInside check if a Coordinate is in the board limit
     * @return true if the coordiante is in the board else function return false
     */
    bool isInside(Coordinate);

    /**
     * @brief isInside check if a Coordinate is in the board limit with 2 others parameters
     * int are the line and the column add to the coordiante (in this order line than column)
     * @return true if the coordiante is in the board else function return false
     */
    bool isInside(Coordinate,int,int);

    /**
     * @brief move change the position of a Piece to a coordinate
     * there is a int parameter it's the maximal number of square move
     */
    void move(Piece&,Coordinate,int&);

    /**
     * @brief check_valid_pass check if a player A can pass to a player B
     * @return a vector of Piece it's all Piece how can have a pass from A
     */
    std::vector<Piece> check_valid_pass(Piece);

    /**
     * @brief pass give the ball from A player to B player where A and B are Piece parameters
     * there is another parameter (int) is the pass left for know if A can pass B
     */
    void pass(Piece&,Piece&, int&);

    /**
     * @brief foul_play is the facade of recurrcif function
     * set the status GameOver if there is a foul play
     * @return the team how have done the foul play
     */
    Team foul_play();

    /**
     * @brief check_enemy check if a enemy is on front of a specified Piece
     * and increment a variable if there is a enemy
     */
    void check_enemy(Piece,int&);

    /**
     * @brief foul_play is a recurrcif function how check
     * for each player if they have a enemy on front and if
     * they are un foul play composition
     * @return true if they are in foul play else function return false
     */
    bool foul_play(Piece,int, int);

    /**
     * @brief getPiece get the Piece at a specified coordiante
     * @return a Piece
     */
    Piece& getPiece(Coordinate);

    /**
     * @brief first_piece get the first Piece in the first column
     * @return a Piece
     */
    Piece first_piece(Team);

    /**
     * @brief print show in terminal de state of the game
     */
    void print();
};

#endif // BOARD_H
