package Company;

public class Game {
    private String name;
    private final Ganre ganre;
    private final GameType type;

    private Game(String name, Ganre ganre, GameType type)
    {
        this.name = name;
        this.ganre = ganre;
        this.type = type;
    }

    public String getName()
    {
        return name;
    }

    public Ganre getGanre()
    {
        return ganre;
    }

    public GameType getType()
    {
        return type;
    }

    public static class GameDisk{
        private final String description;
        private final Game data;

        private GameDisk(String name, Ganre ganre, String description)
        {
            this.data = new Game(name, ganre, GameType.Physical);
            this.description = description;
        }

        public String getDescription()
        {
            return description;
        }

        public Game getData()
        {
            return data;
        }
    }

    public static class VirtualGame{
        private int rating;
        private final Game data;

        private VirtualGame(String name, Ganre ganre)
        {
            this.data = new Game(name, ganre, GameType.Virtual);
        }

        public int getRating()
        {
            return rating;
        }

        public void setRating(int rating)
        {
            if(rating >= 0 && rating <= 5)
            {
                this.rating = rating;
            }
            else
            {
                System.out.println("Invalid rating");
            }
        }

        public Game getData()
        {
            return data;
        }

    }

    public static GameDisk getDisk(String name, Ganre ganre, String description)
    {
        return new GameDisk(name, ganre, description);
    }

    public static VirtualGame getVirtualGame(String name, Ganre ganre)
    {
        return new VirtualGame(name, ganre);
    }
}
