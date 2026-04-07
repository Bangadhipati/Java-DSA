class Robot {
    private int w;
    private int h;
    private int perimeter;
    private int pos;
    private boolean hasMoved;

    public Robot(int width, int height) {
        this.w = width;
        this.h = height;
        // Calculate the exact number of steps to make one full loop
        this.perimeter = 2 * (w - 1) + 2 * (h - 1);
        this.pos = 0;
        this.hasMoved = false;
    }
    
    public void step(int num) {
        // Fast-forward through redundant laps using modulo
        pos = (pos + num) % perimeter;
        hasMoved = true;
    }
    
    public int[] getPos() {
        // Unfold the 1D position onto the 4 edges of the grid
        
        // At origin
        if (pos == 0) return new int[]{0, 0};
        
        // On the Bottom edge (moving East)
        if (pos <= w - 1) {
            return new int[]{pos, 0};
        }
        
        // On the Right edge (moving North)
        if (pos <= (w - 1) + (h - 1)) {
            return new int[]{w - 1, pos - (w - 1)};
        }
        
        // On the Top edge (moving West)
        if (pos <= 2 * (w - 1) + (h - 1)) {
            return new int[]{(w - 1) - (pos - (w + h - 2)), h - 1};
        }
        
        // On the Left edge (moving South)
        return new int[]{0, (h - 1) - (pos - (2 * w + h - 3))};
    }
    
    public String getDir() {
        // Determine direction based on which edge the robot is currently on
        
        // The edge case: at origin, it faces South IF it has moved, otherwise East
        if (pos == 0) return hasMoved ? "South" : "East";
        
        if (pos <= w - 1) return "East";
        if (pos <= (w - 1) + (h - 1)) return "North";
        if (pos <= 2 * (w - 1) + (h - 1)) return "West";
        
        return "South";
    }
}