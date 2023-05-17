/**
 * When a subclass inherits from a superclass, it also inherits its methods;
 * however, it can also override the superclass methods (as well as declare and
 * implement new ones).
 * Consider the following Sports class given. Next, we create a Soccer class
 * that inherits from the Sports class. We can override the getName(),
 * getPlayers() & printTeamMembers() methods and return a different,
 * subclass-specific string.
 * We will return "Overwritten Soccer" in getName(), 11 in getPlayers() and make
 * it such that printTeamMembers() returns the same string as Sports but with
 * its new values.
 */
public class Lab01P3Wrapper {

    public static class Sports {
        private String name;
        private int players;
        public String testValue; // DO NOT DELETE THIS

        public Sports(String name, int players) {
            this.name = name;
            this.players = players;
        }

        public String getName() {
            return name;
        }

        public int getPlayers() {
            return players;
        }

        public String printTeamMembers() {
            return "Each team has " + getPlayers() + " players in " + getName();
        }

        // DO NOT DELETE THIS
        public void setTestValue() {
            testValue = printTeamMembers();
        }
    }

    public static class Soccer extends Sports {

        public Soccer(String name, int players) {
            super(name, players);
        }

        /* TODO ADD OVERWRITTEN METHODS HERE */
        @Override
        public String getName() {
            return "Overwritten Soccer";
        }

        @Override
        public int getPlayers() {
            return 11;
        }

        @Override
        public String printTeamMembers() {
            return "Each team has " + getPlayers() + " players in " + getName();
        }
    }
}