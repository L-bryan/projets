#ifndef GAME_H
#define GAME_H

#include "board.h"
#include "team.h"
#include "piece.h"
#include "coordinate.h"
#include "goal.h"
#include "gamestatus.h"

class Game
{
    Board board;
    int move;
    int handoff;
    GameStatus status;
    Team current_team;
    Goal blue_goal;
    Goal red_goal;
public:

    /**
     * @brief Game Defaulte constructor of Game
     */
    Game();

    /**
     * @brief getPlayer get the Piece at a coordiante
     * @return a Piece
     */
    Piece& getPlayer(Coordinate);

    /**
     * @brief getBoard get the board
     * @return a Board
     */
    Board getBoard();

    /**
     * @brief getMove get the move left for a team
     * @return a int
     */
    int getMove();

    /**
     * @brief gethandoff get the pass left for a team
     * @return a int
     */
    int gethandoff();

    /**
     * @brief getCurrentTeam get the current team how are playing
     * @return a enum of Team
     */
    Team getCurrentTeam();

    /**
     * @brief nextTurn change to the next turn
     * the function reset the move and handoff
     * change the currentTeam
     * and check if there was a foul play
     */
    void nextTurn();

    /**
     * @brief foul_play check if there is a foul play
     * @return true if there is one else the function return false
     */
    bool foul_play();

    /**
     * @brief move_player move a Piece to a coordiante
     */
    void move_player(Piece&,Coordinate);

    /**
     * @brief make_a_pass make a pass from a Piece A to a Piece B
     */
    void make_a_pass(Piece&,Piece&);

    /**
     * @brief isGameOver check if the game is over
     * @return true if the state GAMESTATUS is on GAME_IS_OVER
     * else the function return false
     */
    bool isGameOver();

    /**
     * @brief getWiner get the winner Team
     * @return a enum of Team
     */
    Team getWiner();

    /**
     * @brief print print in terminal the state of the game
     */
    void print();
};

#endif // GAME_H
