#include "windows.h"

Windows::Windows(QWidget *parent) :QWidget(parent),
    game(new Game())
{
//    b = new BoardGui(game,this);
//    setMinimumSize(QSize(1000,800));
//    b->setMinimumSize(QSize(750,750));
    test = new Test(game,this);
}
Windows::~Windows(){
    delete game;
    delete test;
}
