#ifndef WINDOWS_H
#define WINDOWS_H

#include <QWidget>
#include <QGridLayout>

#include "game.h"
#include "boardgui.h"
#include "test.h"

class Windows : public QWidget
{
    BoardGui* b;
    Game* game;
    Test* test;
public:
    explicit Windows(QWidget *parent = nullptr);
     ~Windows();
};

#endif // WINDOWS_H
