package model;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public abstract class GraphData {
    protected String url;
    protected String name;

    public abstract ArrayList<?> getData() throws IOException, InterruptedException;
    public abstract String getName();
    public abstract String[] getXAxis();
    public abstract float[] getYAxis();
    public abstract void setName(String s);
    public abstract void setStartDate(LocalDate startDate);
    public abstract void setEndDate(LocalDate endDate);
}
