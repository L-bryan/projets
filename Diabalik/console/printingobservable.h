#ifndef PRINTINGOBSERVABLE_H
#define PRINTINGOBSERVABLE_H

#include "gameobserver.h"
#include "gameevent.h"
#include <iostream>

class PrintingObserver : public GameObserver
{
public:
    void update(GameEvent e) override;
};
void PrintingObserver::update(GameEvent e){

    std::cout << "joueur courrant -> ";
    if(e.getGame().getCurrentTeam() == Team::BLUE){
        std::cout<<"BLUE"<< std::endl;
    }else{
        std::cout<<"RED"<< std::endl;
    }
    std::cout << "nombre de deplacement disponible -> " << e.getGame().getMove() << std::endl;
    std::cout << "nombre de lancer disponible -> " << e.getGame().gethandoff() << std::endl;

    int line = 0;

    for(std::vector<Piece> v : e.getGame().getBoard().getBoard()){
        std::cout << "L " <<line << " -> ";
        for(Piece p : v){
            if(p.getTeam() == Team::BLUE){
                if(p.haveBall()){
                    std::cout << " B";
                }else{
                    std::cout << " b";
                }
            }else if(p.getTeam() == Team::RED){
                if(p.haveBall()){
                    std::cout << " R";
                }else{
                    std::cout << " r";
                }
            }else{
                std::cout << " .";
            }

        }
        line++;
        std::cout<<std::endl;
    }
    std::cout<<std::endl;
}
#endif // PRINTINGOBSERVABLE_H
