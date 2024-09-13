package Company;

import java.util.Arrays;
import java.util.Comparator;

public class PlayRoom {
    public static void main(String[] args)
    {
        Game.GameDisk[] diskGames = {
                Game.getDisk("name1", Ganre.Race, "desc1"),
                Game.getDisk("name2", Ganre.Action, "desc2"),
                Game.getDisk("name3", Ganre.Sport, "desc3"),
                Game.getDisk("name4", Ganre.Action, "desc4")
        };

        Game.VirtualGame[] virtualGames = {
                Game.getVirtualGame("name1", Ganre.Race),
                Game.getVirtualGame("name2", Ganre.Action),
                Game.getVirtualGame("name3", Ganre.Sport),
                Game.getVirtualGame("name4", Ganre.Action)
        };

        virtualGames[0].setRating(2);
        virtualGames[1].setRating(1);
        virtualGames[2].setRating(3);
        virtualGames[3].setRating(5);


        GameConsole gameConsole = new GameConsole(Brand.Sony, "XC123QeWR");

        Arrays.sort(diskGames, new Comparator<Game.GameDisk>() {
            @Override
            public int compare(Game.GameDisk a, Game.GameDisk b) {
                return a.getData().getGanre().compareTo(b.getData().getGanre());
            }
        });

        Arrays.sort(virtualGames, new Comparator<Game.VirtualGame>() {
            @Override
            public int compare(Game.VirtualGame a, Game.VirtualGame b) {
                return Integer.compare(a.getRating(), b.getRating());
            }
        });

        System.out.println("Physical Games sorted by Genre:");
        for (Game.GameDisk game : diskGames) {
            System.out.println(game.getData().getName() + " - " + game.getData().getGanre());
        }

        System.out.println("\nVirtual Games sorted by Rating:");
        for (Game.VirtualGame game : virtualGames) {
            System.out.println(game.getData().getName() + " - Rating: " + game.getRating());
        }

        gameConsole.getFirstGamepad().powerOn();
        gameConsole.getSecondGamepad().powerOn();

        System.out.println(gameConsole.getIsOn());

        System.out.println(gameConsole.getFirstGamepad().getConnectedNumber());
        System.out.println(gameConsole.getFirstGamepad().getIsOn());
        System.out.println(gameConsole.getSecondGamepad().getConnectedNumber());
        System.out.println(gameConsole.getSecondGamepad().getIsOn());

        gameConsole.getFirstGamepad().powerOff();
        System.out.println(gameConsole.getFirstGamepad().getConnectedNumber());
        System.out.println(gameConsole.getFirstGamepad().getIsOn());
        System.out.println(gameConsole.getSecondGamepad().getConnectedNumber());
        System.out.println(gameConsole.getSecondGamepad().getIsOn());

        gameConsole.loadGame(diskGames[0].getData());

        gameConsole.getFirstGamepad().powerOff();
        gameConsole.getSecondGamepad().powerOff();
        for(int i = 0; i < 7; i++)
        {
            gameConsole.playGame();
        }
    }
}
