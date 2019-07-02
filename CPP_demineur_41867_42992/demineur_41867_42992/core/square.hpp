#ifndef SQUARE_HPP
#define SQUARE_HPP

#include "state.hpp"
#include "coordinate.hpp"

/**
 * @brief The Square class
 */
class Square
{
private:
    State state;
    bool mined;
    int NeighbourMined;
    Coordinate coordinate;
public:
    /**
     * Construct a square object,
     * this square can be mined or not
     * @brief Square object
     * @param mined when is true the square is mined
     */
    Square(bool mined);

    /**
     * @brief getState get the state of the square
     * @return the state attribute of square
     */
    inline State getState();

    /**
     * @brief getMined dermined if a square is mined
     * @return return true if the square is mined else return not
     */
    inline bool isMined();

    /**
     * @brief getNeighbourMined get the number of mined Neighbour
     * @return the int number of NeighbourMined
     */
    inline int getNeighbourMined();

    /**
     * @brief setState set the state to:
     * <ul>
     *  <li>marked</li>
     *  <li>discover</li>
     *  <li>hidden</li>
     * </ul>
     * @param state is a State Enum
     */
    void setState(State state);
};

#endif // SQUARE_HPP
