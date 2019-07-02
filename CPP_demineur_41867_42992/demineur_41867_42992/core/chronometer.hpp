#ifndef CHRONOMETER_HPP
#define CHRONOMETER_HPP

/**
 * @brief The Chronometer class
 */
class Chronometer
{
private:
    int second;
public:

    /**
     * Construct a chronometer object
     * it's a timer in second
     * @brief Chronometer object
     */
    Chronometer();

    /**
     * @brief getSecond the second of chronometer
     * @return int the second of chronometer
     */
    inline int getSecond();
};

#endif // CHRONOMETER_HPP
