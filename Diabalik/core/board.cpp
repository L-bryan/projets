#include "board.h"
#include <iostream>

Board::Board()
{
    std::vector<Piece> v;
    for(int i=0; i< WIDTH;i++){
        v.clear();
        for(int j=0; j<HIGHT; j++){
            v.push_back(Piece());
        }
        board.push_back(v);
    }

    std::vector<Piece>& v_start_blue_team = board.data()[0];
    for(unsigned column = 0; column < v_start_blue_team.size() ;column++){
        v_start_blue_team.data()[column] =
                Piece(Team::BLUE,false,Coordinate(0,column));
        if(column == 3){ v_start_blue_team.data()[column].setball(true);}
    }
    std::vector<Piece>& v_start_red_team = board.data()[board.size()-1];
    for(unsigned column = 0; column < v_start_red_team.size() ;column++){
        v_start_red_team.data()[column] =
                Piece(Team::RED,false,Coordinate(v_start_red_team.size()-1,column));
        if(column == 3){v_start_red_team.data()[column].setball(true);}
    }

}

bool Board::isPosFree(Coordinate pos)
{
    return board.data()[pos.getX()].data()[pos.getY()].getTeam() == Team::NONE;
}

bool Board::isInside(Coordinate c)
{
    return (c.getX() >= 0 && c.getX() < WIDTH
            && c.getY() >= 0 && c.getY() < HIGHT);
}

bool Board::isInside(Coordinate c ,int x,int y)
{
    return (c.getX()+x >= 0 && c.getX()+x < WIDTH
            && c.getY()+y >= 0 && c.getY()+y < HIGHT);
}

//void Board::move(Piece& p, Coordinate pos, int& power)
//{
//    Coordinate old_pos = p.getCoordinate();
//    int x = p.getCoordinate().getX() - pos.getX();
//    int y = p.getCoordinate().getY() - pos.getY();
//    //vérifie que le movement est possible
//    int val_abs = abs(x-y);
//    if( val_abs((val_abs%2 == 0) && (power == 2)) || ((val_abs%2 != 0) && (power >= 1))){
//        if(val_abs%2 == 0){ power -=2;}else{power--;}
//        p.move(pos);//change de piece coordinate
//        board.data()[pos.getX()].data()[pos.getY()] = p; //move in board de piece
//        board.data()[old_pos.getX()]
//             .data()[old_pos.getY()] = Piece();//set null old pos
//    }
//}

void Board::move(Piece& p, Coordinate pos, int& power)
{
    bool isPosfind = false;
    std::vector<Coordinate> pos_valide_1;
    std::vector<Coordinate> pos_valide_2;
    Coordinate old_pos = p.getCoordinate();

    //check les cotés
    if(power >=1){
        for(int i = -1;i<=1;i++){
            if( i != 0){//0 = old_pos
                if(this->isInside(old_pos,i,0) && this->isPosFree(Coordinate(old_pos.getX()+i,old_pos.getY()))){
                    pos_valide_1.push_back(Coordinate(old_pos.getX()+i,old_pos.getY()));
                }
                if(this->isInside(old_pos,0,i) && this->isPosFree(Coordinate(old_pos.getX(),old_pos.getY()+i))){
                    pos_valide_1.push_back(Coordinate(old_pos.getX(),old_pos.getY()+i));
                }
            }
        }

        for(Coordinate& c : pos_valide_1){
            if(pos == c){
                isPosfind = true;
                power--;
                break;
            }
        }

        if(isPosfind){
            p.move(pos);//change de piece coordinate
            board.data()[pos.getX()].data()[pos.getY()] = p; //move in board de piece
            board.data()[old_pos.getX()]
                 .data()[old_pos.getY()] = Piece();//set null old pos
        }else{
            if(power == 2){
                for(Coordinate c : pos_valide_1){
                    for(int i = -1;i<=1;i++){
                        if( i != 0){
                            if(this->isInside(c,i,0) && this->isPosFree(Coordinate(c.getX()+i,c.getY()))){
                                pos_valide_2.push_back(Coordinate(c.getX()+i,c.getY()));
                            }
                            if(this->isInside(c,0,i) && this->isPosFree(Coordinate(c.getX(),c.getY()+i))){
                                pos_valide_2.push_back(Coordinate(c.getX(),c.getY()+i));
                            }
                        }
                    }
                }
                for(Coordinate& c : pos_valide_2){
                    if(c == pos){
                        power-=2;
                        p.move(pos);//change de piece coordinate
                        board.data()[pos.getX()].data()[pos.getY()] = p; //move in board de piece
                        board.data()[old_pos.getX()]
                             .data()[old_pos.getY()] = Piece();//set null old pos
                        break;
                    }
                }
            }
        }
    }
}

std::vector<Piece> Board::check_valid_pass(Piece p) // 2 boucle problème logique
{
    Team my_t = p.getTeam();
    Team enemy_t;

    if(my_t == Team::BLUE){
        enemy_t = Team::RED;
    }else{
        enemy_t = Team::BLUE;
    }

    std::vector<Piece> valid_pass;
    Coordinate pos = p.getCoordinate();

    //top
    for(int x = pos.getX(); x>=0;x--){
        Piece tmp = board.data()[x].data()[pos.getY()];
        if(tmp.getTeam() == enemy_t){
            break;
        }else if(tmp.getTeam() == my_t){
            valid_pass.push_back(tmp);
        }

    }
    //bottom
    for(unsigned x = pos.getX(); x<board.size();x++){
        Piece tmp = board.data()[x].data()[pos.getY()];
        if(tmp.getTeam() == enemy_t){
            break;
        }else if(tmp.getTeam() == my_t){
            valid_pass.push_back(tmp);
        }

    }
    //left
    for(int y = pos.getY(); y>=0;y--){
        Piece tmp = board.data()[pos.getX()].data()[y];
        if(tmp.getTeam() == enemy_t){
            break;
        }else if(tmp.getTeam() == my_t){
            valid_pass.push_back(tmp);
        }

    }
    //right
    for(unsigned y = pos.getY(); y<board.data()[pos.getX()].size();y++){
        Piece tmp = board.data()[pos.getX()].data()[y];
        if(tmp.getTeam() == enemy_t){
            break;
        }else if(tmp.getTeam() == my_t){
            valid_pass.push_back(tmp);
        }

    }
    //top - left
//    for(int x = pos.getX(); x>=0; x--){
//        for(int y = pos.getY(); y>=0; y--){
//            Piece tmp = board.data()[x].data()[y];
//            if(tmp.getTeam() == enemy_t){
//                break;
//            }else if(tmp.getTeam() == my_t){
//                valid_pass.push_back(tmp);
//            }
//        }
//    }

    {
        int x = pos.getX()-1;
        int y = pos.getY()-1;
        while(x>=0 && y>=0){
            Piece tmp = board.data()[x].data()[y];
            if(tmp.getTeam() == enemy_t){
                break;
            }else if(tmp.getTeam() == my_t){
                valid_pass.push_back(tmp);
            }
            x--; y--;
        }
    }

    //top - right
//    for(int x = pos.getX(); x>=0; x--){
//        for(unsigned y = pos.getY(); y<board.data()[x].size(); y++){
//            Piece tmp = board.data()[x].data()[y];
//            if(tmp.getTeam() == enemy_t){
//                break;
//            }else if(tmp.getTeam() == my_t){
//                valid_pass.push_back(tmp);
//            }
//        }
//    }

    {
        int x = pos.getX()-1;
        int y = pos.getY()+1;
        while(x>=0 && y<board.data()[x].size()){
            Piece tmp = board.data()[x].data()[y];
            if(tmp.getTeam() == enemy_t){
                break;
            }else if(tmp.getTeam() == my_t){
                valid_pass.push_back(tmp);
            }
            x--; y++;
        }
    }

    //bottom - left
//    for(unsigned x = pos.getX(); x<board.size(); x++){
//        for(int y = pos.getY(); y>=0; y--){
//            Piece tmp = board.data()[x].data()[y];
//            if(tmp.getTeam() == enemy_t){
//                break;
//            }else if(tmp.getTeam() == my_t){
//                valid_pass.push_back(tmp);
//            }
//        }
//    }

    {
        int x = pos.getX()+1;
        int y = pos.getY()-1;
        while(x<board.size() && y>=0){
            Piece tmp = board.data()[x].data()[y];
            if(tmp.getTeam() == enemy_t){
                break;
            }else if(tmp.getTeam() == my_t){
                valid_pass.push_back(tmp);
            }
            x++; y--;
        }
    }

    //bottom - right
//    for(unsigned x = pos.getX(); x<board.size(); x++){
//        for(unsigned y = pos.getY(); y<board.data()[x].size(); y++){
//            Piece tmp = board.data()[x].data()[y];
//            if(tmp.getTeam() == enemy_t){
//                break;
//            }else if(tmp.getTeam() == my_t){
//                valid_pass.push_back(tmp);
//            }
//        }
//    }

    {
        int x = pos.getX()+1;
        int y = pos.getY()+1;
        while(x<board.size() && y<board.data()[x].size()){
            Piece tmp = board.data()[x].data()[y];
            if(tmp.getTeam() == enemy_t){
                break;
            }else if(tmp.getTeam() == my_t){
                valid_pass.push_back(tmp);
            }
            x++; y++;
        }
    }

    return valid_pass;
}

void Board::pass(Piece& ps, Piece& pd, int& handoff)
{
   //vérifie que la passe est possible
    for(Piece p : check_valid_pass(ps) ){
        if(p.getCoordinate() == pd.getCoordinate()){
            ps.setball(false);
            pd.setball(true);
            board.data()[ps.getCoordinate().getX()]
                 .data()[ps.getCoordinate().getY()].setball(false);
            board.data()[pd.getCoordinate().getX()]
                 .data()[pd.getCoordinate().getY()].setball(true);
            handoff--;
            break;
        }
    }
}

Piece Board::first_piece(Team t){

    Piece first;
    for(std::vector<Piece> v : board){
        if(v.data()[0].getTeam() == t){
            first = v.data()[0];
            break;
        }
    }
    return first;
}

void Board::check_enemy(Piece p ,int& cpt)
{
    std::cout<<"befor check enemy -------------> cpt "<<cpt<<std::endl;
    if(p.getTeam() == Team::BLUE){
        if(isInside(p.getCoordinate(),1,0) &&
                board.data()[p.getCoordinate().getX()+1]
                .data()[p.getCoordinate().getY()]
                .getTeam() == Team::RED){cpt+=1;}
    }else{
        if(isInside(p.getCoordinate(),-1,0) &&
                board.data()[p.getCoordinate().getX()-1]
                .data()[p.getCoordinate().getY()]
                .getTeam() == Team::BLUE){cpt+=1;}
    }
    std::cout<<"after check enemy -------------> cpt "<<cpt<<std::endl;
}

bool Board::foul_play(Piece p,int nb, int cpt)
{
    std::cout<<"befor check foul play -------------> nb "<<nb<<" cpt enemy "<<cpt<<std::endl;
    const int PIECE_NB_MAX = 7;
    const int MIN_PIECE_FOR_FOUL_PLAY = 3;

    int piece_nb = nb;
    int cpt_enemy = cpt;
    check_enemy(p,cpt_enemy);
    Team team = p.getTeam();
    std::cout<<"after check foul play -------------> nb "<<piece_nb<<" cpt enemy "<<cpt_enemy<<std::endl;
    std::cout<<std::endl;
    if(piece_nb == PIECE_NB_MAX){
        return cpt_enemy >= MIN_PIECE_FOR_FOUL_PLAY;
    }else{
        if(isInside(p.getCoordinate(),-1,1) &&
                board.data()[p.getCoordinate().getX()-1] //haut droite
                .data()[p.getCoordinate().getY()+1]
                .getTeam() == team){

            return foul_play(board.data()[p.getCoordinate().getX()-1]
                    .data()[p.getCoordinate().getY()+1],piece_nb+1,cpt_enemy);

        }else if(isInside(p.getCoordinate(),0,1) &&
                 board.data()[p.getCoordinate().getX()] // droite
                      .data()[p.getCoordinate().getY()+1]
                      .getTeam() == team){

            return foul_play(board.data()[p.getCoordinate().getX()]
                    .data()[p.getCoordinate().getY()+1],piece_nb+1,cpt_enemy);

        }else if(isInside(p.getCoordinate(),1,1) &&
                 board.data()[p.getCoordinate().getX()+1] //bas droite
                      .data()[p.getCoordinate().getY()+1]
                      .getTeam() == team){

            return foul_play(board.data()[p.getCoordinate().getX()+1]
                    .data()[p.getCoordinate().getY()+1],piece_nb+1,cpt_enemy);

        }else{
            return false;
        }
    }

}

Team Board::foul_play()
{
    if(foul_play(first_piece(Team::BLUE),1,0)){
        return Team::BLUE;
    }else if(foul_play(first_piece(Team::RED),1,0)){
        return Team::RED;
    }else{
        return Team::NONE;
    }
}

Piece& Board::getPiece(Coordinate c)
{
    return board.data()[c.getX()].data()[c.getY()];
}

std::vector<std::vector<Piece>> Board::getBoard(){
    return board;
}

void Board::print()
{
    int line = 0;

    for(std::vector<Piece> v : board){
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
