#include "game.h"

Game::Game() : board(Board()), move(2), handoff(1),
    status(GameStatus::GAME_IN_PROGRESS),
    current_team(Team::BLUE), blue_goal(Goal(Team::BLUE)),
                              red_goal(Goal(Team::RED))
{}

Piece& Game::getPlayer(Coordinate pos)
{
    return board.getPiece(pos);
}

int Game::getMove()
{
    return move;
}
int Game::gethandoff()
{
    return handoff;
}

Team Game::getCurrentTeam()
{
    return current_team;
}

void Game::nextTurn()
{
    //vérifier si il y a un anti-jeu (le jeu s'arret)
    if(foul_play()){status = GameStatus::GAME_OVER;}

    if(!isGameOver()){
        //vérifier qu'il a fait ou moins 1 déplacement ou une passe
        if(move<2 || handoff == 0){
            move = 2;
            handoff = 1;
            if(current_team == Team::BLUE){
                current_team = Team::RED;
            }else{
                current_team = Team::BLUE;
            }
        }
    }
}
bool Game::foul_play()
{
    return board.foul_play() != Team::NONE;
}
void Game::move_player(Piece& p,Coordinate pos)//&
{
    //test
    //joueur de l'équipe
    if (p.getTeam() == current_team){
        //ne pas avoir le ballon
        if(!p.haveBall()){
            //avoir encore des déplacement possible
            if(move > 0){
                //case voisine libre
                if(board.isPosFree(pos)){
                    board.move(p,pos,move);
                }
            }
        }
    }
}
void Game::make_a_pass(Piece& p,Piece& pp)//&
{
    //test
    //joueur de l'équipe
    if (p.getTeam() == current_team && pp.getTeam() == current_team){
        //avoir le ballon
        if(p.haveBall()){
            //avoir des lancé possible
            if(handoff > 0){
                //avoir des joueurs accèssible
                //condistion qui permet de vérifier si une passe est possible xy -> xy
                board.pass(p,pp,handoff);
                //check si il y a goal (fin de partie)
                if(current_team == Team::BLUE){
                    if(pp.haveBall() && red_goal.isInside(pp.getCoordinate())){
                        status = GameStatus::GAME_OVER;
                    }
                }else{
                    if(pp.haveBall() && blue_goal.isInside(pp.getCoordinate())){
                        status = GameStatus::GAME_OVER;
                    }
                }
            }
        }
    }
}

bool Game::isGameOver()
{
    return status == GameStatus::GAME_OVER;//foul_play(); // || goal mais goal déterm. après une passe ?!
}

Team Game::getWiner(){
    if(isGameOver()){
        if(foul_play()){
            if(board.foul_play() == Team::BLUE){
                return Team::RED;
            }else{
                return Team::BLUE;
            }
        }else{
            return current_team;
        }
    }
}

Board Game::getBoard(){
    return board;
}

void Game::print(){
    board.print();
}
