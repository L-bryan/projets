#ifndef GAMESUBJECT_H
#define GAMESUBJECT_H

#include "game.h"
#include "gameobserver.h"
#include "gameevent.h"
#include <vector>

class GameSubject : public Game
{
private:
    std::vector<GameObserver*> observers;
public:
    GameSubject();
    inline void registerObserver(GameObserver& obs);
    inline void move(Piece& ,Coordinate);
    inline void pass(Piece&, Piece&);
    inline bool isGameOver();
};

GameSubject::GameSubject() :
    Game(){}

void GameSubject::registerObserver(GameObserver &obs){
    observers.push_back(&obs);
}
void GameSubject::move(Piece& p,Coordinate coordinate)
{
    Game::move_player(p,coordinate);
    GameEvent e(*this);
    for(GameObserver* obs : observers){
        obs->update(e);
    }
}
void GameSubject::pass(Piece& p,Piece& pp)
{
    Game::make_a_pass(p,pp);
    GameEvent e(*this);
    for(GameObserver* obs : observers){
        obs->update(e);
    }
}

bool GameSubject::isGameOver()
{
    GameEvent e(*this);
    for(GameObserver* obs : observers){
        obs->update(e);
    }
    return Game::isGameOver();
}

#endif // GAMESUBJECT_H
