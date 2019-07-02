#ifndef GAMEOBSERVER_H
#define GAMEOBSERVER_H

#include "game.h"
#include "gameevent.h"
class GameObserver
{
public:
    virtual void update(GameEvent e) = 0;
};
#endif // GAMEOBSERVER_H
