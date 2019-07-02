#include <QApplication>
#include <QWidget>

#include "Windows.h"
int main(int argc, char *argv[])
{
    QApplication app(argc, argv);

    Windows windows;
    windows.show();

    return app.exec();
}
