sTEMPLATE = lib
TARGET = libcore
DESTDIR = ../lib

#CONFIG += console c++11
#CONFIG -= app_bundle
#CONFIG -= qt

SOURCES += main.cpp

HEADERS += \
    coordinate.hpp \
    square.hpp \
    board.hpp \
    player.hpp \
    chronometer.hpp \
    playerscore.hpp \
    game.hpp \
    highscore.hpp \
    state.hpp
