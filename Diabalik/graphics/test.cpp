#include "test.h"
#include "ui_test.h"

Test::Test(Game* g,QWidget *parent) :
    QWidget(parent), game(g),
    ui(new Ui::Test)
{
    ui->setupUi(game,this,this);
}

Test::~Test()
{
    delete ui;
}

void Test::on_pushButton_clicked()
{
    game->nextTurn();
    ui->widget->update();
}

void Test::on_move_windowTitleChanged(const QString &title)
{
    ui->move->setText(title);
}

void Test::on_passe_windowTitleChanged(const QString &title)
{
    ui->passe->setText(title);
}

void Test::on_label_windowTitleChanged(const QString &title)
{
    ui->label->setText(title);
}

void Test::on_winer_windowTitleChanged(const QString &title)
{
    ui->winer->setText(title);
}
