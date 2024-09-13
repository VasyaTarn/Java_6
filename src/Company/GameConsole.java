package Company;

public class GameConsole implements Powered {
    private final Brand brand;
    private String model;
    private final String serial;
    private Gamepad firstGamepad;
    private Gamepad secondGamepad;
    private boolean isOn;
    private Game activeGame;
    private int waitingCounter;

    public GameConsole(Brand brand, String serial)
    {
        this.brand = brand;
        this.serial = serial;

        this.firstGamepad = new Gamepad(this.brand, 1);
        this.secondGamepad = new Gamepad(this.brand, 2);
    }

    @Override
    public void powerOn()
    {
        isOn = true;
    }

    @Override
    public void powerOff()
    {
        isOn = false;
    }

    public Brand getBrand()
    {
        return brand;
    }

    public String getSerial()
    {
        return serial;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Gamepad getFirstGamepad() {
        return firstGamepad;
    }

    public void setFirstGamepad(Gamepad firstGamepad) {
        this.firstGamepad = firstGamepad;
    }

    public Gamepad getSecondGamepad() {
        return secondGamepad;
    }

    public void setSecondGamepad(Gamepad secondGamepad) {
        this.secondGamepad = secondGamepad;
    }

    public boolean getIsOn() {
        return isOn;
    }

    public void setIsOn(boolean isOn) {
        this.isOn = isOn;
    }

    public Game getActiveGame()
    {
        return activeGame;
    }

    public void setActiveGame(Game activeGame)
    {
        this.activeGame = activeGame;
    }

    public class Gamepad implements Powered {
        private final Brand brand;
        private final String consoleSerial;
        private int connectedNumber;
        private Color color;
        private double chargeLevel = 100.0;
        private boolean isOn;

        public Gamepad(Brand brand, int connectedNumber)
        {
            this.brand = brand;
            this.connectedNumber = connectedNumber;
            this.consoleSerial = GameConsole.this.serial;
        }

        @Override
        public void powerOn()
        {
            this.isOn = true;
            if(!GameConsole.this.isOn)
            {
                GameConsole.this.isOn = true;
            }
        }

        @Override
        public void powerOff()
        {
            this.isOn = false;
            if(!GameConsole.this.firstGamepad.isOn && GameConsole.this.secondGamepad.isOn)
            {
                GameConsole.this.secondGamepad.connectedNumber = 1;
                GameConsole.this.firstGamepad.connectedNumber = 2;
            }
        }

        public Brand getBrand()
        {
            return this.brand;
        }

        public String getConsoleSerial()
        {
            return this.consoleSerial;
        }

        public int getConnectedNumber() {
            return connectedNumber;
        }

        public void setConnectedNumber(int connectedNumber) {
            this.connectedNumber = connectedNumber;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public double getChargeLevel() {
            return chargeLevel;
        }

        public void setChargeLevel(double chargeLevel) {
            this.chargeLevel = chargeLevel;
        }

        public boolean getIsOn() {
            return isOn;
        }

        public void setIsOn(boolean isOn) {
            this.isOn = isOn;
        }
    }

    private void checkStatus() throws NoActivityException {
        if (!firstGamepad.getIsOn() && !secondGamepad.getIsOn()) {
            System.out.println("Подключите геймпад");
            waitingCounter++;
            if (waitingCounter > 5) {
                powerOff();
                throw new NoActivityException("Приставка завершает работу из-за отсутствия активности");
            }
        } else {
            waitingCounter = 0;
        }
    }

    public void loadGame(Game game)
    {
        System.out.println("Игра " + game.getName() + " загружается");
        activeGame = game;
    }

    public void playGame()
    {
        try {
            checkStatus();
        } catch (NoActivityException e) {
            System.out.println(e.getMessage());
            return;
        }

        if(activeGame != null)
        {
            System.out.println("Играем в " + activeGame.getName());
            if(firstGamepad.isOn)
            {
                System.out.println("Заряд геймпада " + "#" + firstGamepad.getConnectedNumber() + ": " + firstGamepad.getChargeLevel());
                firstGamepad.setChargeLevel(firstGamepad.getChargeLevel() - 10);
                if(firstGamepad.getChargeLevel() <= 0)
                {
                    firstGamepad.setIsOn(false);
                    System.out.println("Геймпад #" + firstGamepad.getConnectedNumber() + " выключен.");
                }
            }
            if(secondGamepad.isOn)
            {
                System.out.println("Заряд геймпада " + "#" + secondGamepad.getConnectedNumber() + ": " + secondGamepad.getChargeLevel());
                secondGamepad.setChargeLevel(secondGamepad.getChargeLevel() - 10);
                if(secondGamepad.getChargeLevel() <= 0)
                {
                    secondGamepad.setIsOn(false);
                    System.out.println("Геймпад #" + secondGamepad.getConnectedNumber() + " выключен.");
                }
            }
        }
        else
        {
            System.out.println("Нет активных игр");
        }

    }
}
