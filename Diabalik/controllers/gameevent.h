#ifndef GAMEEVENT_H
#define GAMEEVENT_H

#include "game.h"
class GameEvent
{
private:
    Game game;
public:
    GameEvent(Game game);
    inline Game getGame();
};

GameEvent::GameEvent(Game game):game(game){}
Game GameEvent::getGame(){
    return game;
}


#endif // GAMEEVENT_H
