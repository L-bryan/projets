#ifndef SQUAREBUTTON_H
#define SQUAREBUTTON_H

#include <QPushButton>
#include <QMouseEvent>

//#include "game.h"
#include "boardgui.h"

class SquareButton : public QPushButton
{
     Q_OBJECT
    const int line;
    const int column;
    BoardGui* boardgui;
    Game* game;
public:
    SquareButton(Game* game, BoardGui* boardgui, int, int, QWidget *parent = 0);
    int getX() const;
    int getY() const;

private slots:
    void mousePressEvent(QMouseEvent *e);

public slots:
    void play();

signals:
    void leftClicked();
};

#endif // SQUAREBUTTON_H
