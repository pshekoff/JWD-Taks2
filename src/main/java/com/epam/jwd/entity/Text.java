package com.epam.jwd.entity;

import java.util.ArrayList;
import java.util.List;

public class Text {
    private List<Row> rows = new ArrayList<>();

    public Text() {};

    public void addRow(Row row) {
        rows.add(row);
    }

    public List<Row> getRows() {
        return rows;
    }
}
