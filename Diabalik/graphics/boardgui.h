#ifndef BOARDGUI_H
#define BOARDGUI_H

#include <QWidget>
#include <QGridLayout>
#include <QVBoxLayout>
#include <QHBoxLayout>
#include <QLabel>

#include "game.h"
#include "test.h"

class BoardGui : public QWidget
{
    Q_OBJECT
    Game* game;
    QGridLayout* grid;
    Test* test;
    Piece* source;
    Piece* target;
    Coordinate* targetPos;

public:
    explicit BoardGui(Game* game,Test* test,QWidget *parent = nullptr);

    void setPieceSource(Piece*);
    void setPieceTarget(Piece*);
    void setPosTarget(Coordinate*);
    Piece* getPieceSource();
    Piece* getPieceTarget();
    Coordinate* getPosTarget();
    QGridLayout* getGrid();
    void update();
signals:
    leftClicked();
public slots:
    void play();
};

#endif // BOARDGUI_H
