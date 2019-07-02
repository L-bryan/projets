#include <iostream>
#include<string.h>
#include "game.h"
#include "gamesubject.h"
#include "printingobservable.h"
using namespace std;

void play(GameSubject&);
Piece& getPlayer(GameSubject);
Coordinate ask_coordiante();
void printInfo(GameSubject);
string ask();
string askInt(string);
bool isParsable(string);

int main()
{
    GameSubject game;
    PrintingObserver p;
    game.registerObserver(p);
    //Game game;
    play(game);
}

void play(GameSubject& game)
{
    int choice;
    Piece player;
    while(!game.isGameOver()){
        //printInfo(game);
        //game.print();
        cout << "Selection : " << endl;
        cout << "    [1] choisir un joueur" << endl;
        cout << "    [2] fin du tour" << endl;
        choice = stoi((askInt("--> ")));
        switch(choice){
            case 1:
                cout << endl;
                 player = getPlayer(game);
                if(player.haveBall()){
                    cout << "faire une passe a un autre joueur" <<endl;
                    game.make_a_pass(player,getPlayer(game));
                }else{
                    cout << "faire un deplacement" << endl;
                    game.move_player(player,ask_coordiante());
                }
                if(game.getMove() == 0 && game.gethandoff() == 0){game.nextTurn();}
            break;
            case 2:
                game.nextTurn();
            break;
        }
    }
}

Piece& getPlayer(GameSubject game)
{
    cout << "Pour selectionner un joueur ";
    return game.getPlayer(ask_coordiante());
}

Coordinate ask_coordiante()
{
    int x, y;
    cout << "enter les coordonnees" << endl;
    do{
        x = stoi((askInt("  Enter the x value -> ")));
    }while(x>=7 || x<0);

    do{
        y = stoi((askInt("  Enter the y value -> ")));
    }while(y>=7 || y<0);
    return Coordinate(x,y);
}

void printInfo(Game game)
{
    cout << "joueur courrant -> " << game.getCurrentTeam() << endl;
    cout << "nombre de deplacement disponible -> " << game.getMove() << endl;
    cout << "nombre de lancer disponible -> " << game.gethandoff() << endl;
}

string ask()
{
    string line;
    getline(cin,line);
    return line;
}

string askInt(string msg)
{
    bool parsable;
    string line;
    do{
        cout<<msg;
        line = ask();
        parsable = isParsable(line);
    }while(!parsable);
    return line;
}

bool isParsable(string input){
    bool parsable = true;
    try{
        stoi(input);
    }catch(...){
        parsable = false;
    }
    return parsable;
}
