#TEMPLATE = app
#CONFIG += console c++11
#CONFIG -= app_bundle
#CONFIG -= qt

SOURCES += main.cpp \
    windows.cpp \
    boardgui.cpp \
    squarebutton.cpp \
    test.cpp

include(../defaults.pri)

QT += widgets
CONFIG += qt



LIBS += -L../lib -llibcore

INCLUDEPATH += $$PWD/../core
DEPENDPATH += $$PWD/../core

HEADERS += \
    windows.h \
    boardgui.h \
    squarebutton.h \
    test.h

DISTFILES +=

RESOURCES += \
    resources.qrc

FORMS += \
    test.ui
