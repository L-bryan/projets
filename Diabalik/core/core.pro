include(../defaults.pri)
TEMPLATE = lib
TARGET = libcore
DESTDIR = ../lib

HEADERS += \
    coordinate.h \
    game.h \
    gamestatus.h \
    goal.h \
    piece.h \
    team.h \
    board.h

SOURCES += \
    coordinate.cpp \
    game.cpp \
    goal.cpp \
    piece.cpp \
    square.cpp \
    board.cpp
