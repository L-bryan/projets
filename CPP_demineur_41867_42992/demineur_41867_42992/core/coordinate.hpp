#ifndef COORDINATE_HPP
#define COORDINATE_HPP

/**
 * @brief The Coordinate class
 */
class Coordinate
{
private:
    int row;
    int column;
public:

    /**
     * Construct a coordinate object
     * it's a representation of a item who content
     * a row and a column
     * @brief Coordinate object
     * @param int row
     * @param int column
     */
    Coordinate(int row, int column);

    /**
     * @brief getRow get the row
     * @return int the row
     */
    inline int getRow();

    /**
     * @brief getColumn get the column
     * @return int the column
     */
    inline int getColumn();
};

#endif // COORDINATE_HPP
