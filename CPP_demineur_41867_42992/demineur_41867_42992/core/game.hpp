#ifndef GAME_HPP
#define GAME_HPP

#include"board.hpp"
#include"player.hpp"
#include"highscore.hpp"
#include"chronometer.hpp"

/**
 * the core facade
 * @brief The Game class
 */
class Game
{
private:
    Board board;
    Player player;
    HighScore highScore;
    Chronometer chronometer;
public:

    /**
     * @brief Game
     */
    Game() {}

    /**
     * @brief isOver
     * @return
     */
    bool isOver(){}

    /**
     * @brief Start
     */
    void Start(){}
};


#endif // GAME_HPP
