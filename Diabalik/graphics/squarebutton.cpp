#include "squarebutton.h"
#include <stdio.h>
#include <iostream>
SquareButton::SquareButton(Game* game,BoardGui* boardgui,int x, int y,
                           QWidget* parent) :
   QPushButton(parent), line(x),column(y),boardgui(boardgui), game(game)
{

}

int SquareButton::getX() const
{
    return line;
}
int SquareButton::getY() const
{
    return column;
}

void SquareButton::mousePressEvent(QMouseEvent *e){
    emit leftClicked();
}

void SquareButton::play(){

    Piece* p = &game->getBoard().getPiece(Coordinate(line,column));//l'@ de la pièce

    std::cout<<"Piece* p methode play() -> "<<p->getCoordinate().getX()<<":"<<p->getCoordinate().getY()<<std::endl; //---------------

    if(boardgui->getPieceSource() == nullptr
            && p->getTeam() != Team::NONE
            && p->getTeam() == game->getCurrentTeam()){

        boardgui->setPieceSource(p);

        std::cout<<"set source"<<std::endl; //-----------------
    }else if(boardgui->getPieceSource() != nullptr
             && boardgui->getPieceTarget() == nullptr
             && p->getTeam() != Team::NONE
             && p->getTeam() == game->getCurrentTeam()){

        boardgui->setPieceTarget(p);

        std::cout<<"set target"<<std::endl; //-----------------
    }else if(boardgui->getPieceSource() != nullptr
             && p->getTeam() == Team::NONE
             && boardgui->getPosTarget() == nullptr){

        boardgui->setPosTarget(new Coordinate(line,column));

        std::cout<<"set pos"<<std::endl; //-----------------
    }
    if((boardgui->getPieceSource() != nullptr
            && boardgui->getPieceTarget() != nullptr)
            || (boardgui->getPieceSource() != nullptr
                && boardgui->getPosTarget() != nullptr)){

        std::cout<<"lance le play de boardgui"<<std::endl; //-----------------
        boardgui->play();
    }

}
