#ifndef PLAYER_HPP
#define PLAYER_HPP

#include<string>

/**
 * @brief The Player class
 */
class Player
{
private:
    std::string name;
public:

    /**
     * Construct a object Player with a name
     * @brief Player object
     * @param name a string, is the name of the player
     */
    Player(std::string name);

    /**
     * @brief getName of the player
     * @return the string name
     */
    inline std::string getName();
};

#endif // PLAYER_HPP
