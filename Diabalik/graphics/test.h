#ifndef TEST_H
#define TEST_H

#include <QWidget>
#include "game.h"

namespace Ui {
class Test;
}

class Test : public QWidget
{
    Q_OBJECT
    Game* game;
public:
    explicit Test(Game* game,QWidget *parent = 0);
    ~Test();

public slots:
    void on_pushButton_clicked();

    void on_move_windowTitleChanged(const QString &title);

    void on_passe_windowTitleChanged(const QString &title);

    void on_label_windowTitleChanged(const QString &title);

    void on_winer_windowTitleChanged(const QString &title);

private:
    Ui::Test *ui;
};

#endif // TEST_H
