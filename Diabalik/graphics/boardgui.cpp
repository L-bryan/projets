#include "boardgui.h"
#include "squarebutton.h"

#include <iostream>
#include <string>

BoardGui::BoardGui(Game* game,Test* test, QWidget *parent)
    : QWidget(parent), game(game), grid(new QGridLayout(this)),
      test(test), source(nullptr), target(nullptr), targetPos(nullptr)
{
    grid->setSpacing(0);
    grid->setHorizontalSpacing(0);
    grid->setVerticalSpacing(0);
    grid->setRowMinimumHeight(0,0);

    QPushButton* btn;
    SquareButton* sbtn;

    for(int i = 0; i<7; i++){
        for(int j = 0; j<7; j++){
            Piece p = game->getBoard().getBoard().data()[i].data()[j];
            QIcon icon;
            QString str;

            if(p.getTeam() == Team::BLUE){
                if(p.haveBall()){
                    icon.addFile(":/img/blueb.png");
                    str.append("B");
                }else{
                    icon.addFile(":/img/blue.png");
                    str.append("b");
                }
            }else if(p.getTeam() == Team::RED){
                if(p.haveBall()){
                    icon.addFile(":/img/redb.png");
                    str.append("R");
                }else{
                    icon.addFile(":/img/red.png");
                    str.append("r");
                }
            }else{
                icon.addFile(":/img/vide.png");
                str.append("");
            }

            btn = new QPushButton(/*str);*/icon,QString());
            btn->setFixedSize(110,110);
            btn->setIconSize(QSize(100,100));
            sbtn = new SquareButton(game,this,i,j,btn);

            QObject::connect(sbtn,SIGNAL(leftClicked()),sbtn,SLOT(play()));

            grid->addWidget(sbtn,i,j);
        }
    }
}

void BoardGui::play()
{
    if(source != nullptr && target != nullptr){
        std::cout<<"on a un joueur s et d"<<std::endl;
        if(source->haveBall()){
            std::cout<<"il a la balle"<<std::endl;
            game->make_a_pass(*source,*target);
            source = nullptr;
            target = nullptr;
            if(game->getMove() == 0 && game->gethandoff() == 0){game->nextTurn();}
            update();
        }
    }else if(source != nullptr && targetPos != nullptr){
        std::cout<<"on a un joueur et sa futur position"<<std::endl;
        if(!source->haveBall()){
            std::cout<<"il n'a pas la balle"<<std::endl;
            game->move_player(*source,*targetPos);
            source = nullptr;
            delete targetPos;
            targetPos = nullptr;
            if(game->getMove() == 0 && game->gethandoff() == 0){game->nextTurn();}
            update();
        }
    }

}

void BoardGui::update(){

    std::cout<<"update"<<std::endl;
    if(game->isGameOver()){
        if(game->getWiner() == Team::BLUE){
            test->on_winer_windowTitleChanged("TEAM BLEU WIN");
        }else{
            test->on_winer_windowTitleChanged("TEAM ROUGE WIN");
        }
    }
    for(int i = 0; i<7; i++){
        for(int j = 0; j<7; j++){

            SquareButton* btn = dynamic_cast<SquareButton*>(
                        dynamic_cast<QWidget *>(grid->itemAtPosition(i,j)->widget()));
            Piece p = game->getBoard().getBoard().data()[i].data()[j];
            QIcon icon;
            QString str;

            if(p.getTeam() == Team::BLUE){
                if(p.haveBall()){
                    icon.addFile(":/img/blueb.png");
                    str.append("B");
                }else{
                    icon.addFile(":/img/blue.png");
                    str.append("b");
                }
            }else if(p.getTeam() == Team::RED){
                if(p.haveBall()){
                    icon.addFile(":/img/redb.png");
                    str.append("R");
                }else{
                    icon.addFile(":/img/red.png");
                    str.append("r");
                }
            }else{
                icon.addFile(":/img/vide.png");
                str.append(" ");
            }
            btn->setFixedSize(110,110);
            btn->setIconSize(QSize(100,100));
            btn->setIcon(icon);
            //btn->setText(str);

            QString m = "Nombre de movement restant : "
                    + QString::number(game->getMove());
            QString pa = "Nombre de passe restant : "
                    + QString::number(game->gethandoff());
            QString currentTeam;
            if(game->getCurrentTeam() == Team::BLUE){
                currentTeam = QString("Au tour de l'équipe : BLEU");
            }else{
                currentTeam = QString("Au tour de l'équipe : RED");
            }
            test->on_label_windowTitleChanged(currentTeam);
            test->on_move_windowTitleChanged(m);
            test->on_passe_windowTitleChanged(pa);
        }
    }
}

void BoardGui::setPieceSource(Piece* p)
{
    source = p;
}
void BoardGui::setPieceTarget(Piece* p)
{
    target = p;
}
void BoardGui::setPosTarget(Coordinate* c)
{
    targetPos = c;
}

Piece* BoardGui::getPieceSource()
{
    return source;
}
Piece* BoardGui::getPieceTarget()
{
    return target;
}
Coordinate* BoardGui::getPosTarget()
{
    return targetPos;
}

QGridLayout* BoardGui::getGrid()
{
    return grid;
}
