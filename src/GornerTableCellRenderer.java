import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.DefaultTableCellRenderer;

public class GornerTableCellRenderer implements TableCellRenderer {
    private JPanel panel = new JPanel();
    private JLabel label = new JLabel();
    // Ищем ячейки, строковое представление которых совпадает с needle
// (иголкой). Применяется аналогия поиска иголки в стоге сена, в роли
// стога сена - таблица
    private String needle = null;
    private String range = null;
    private DecimalFormat formatter =
            (DecimalFormat)NumberFormat.getInstance();
    public GornerTableCellRenderer() {
// Показывать только 5 знаков после запятой
        formatter.setMaximumFractionDigits(5);
// Не использовать группировку (т.е. не отделять тысячи
// ни запятыми, ни пробелами), т.е. показывать число как "1000",
// а не "1 000" или "1,000"
        formatter.setGroupingUsed(false);
// Установить в качестве разделителя дробной части точку, а не
// запятую. По умолчанию, в региональных настройках
// Россия/Беларусь дробная часть отделяется запятой
        DecimalFormatSymbols dottedDouble =
                formatter.getDecimalFormatSymbols();
        dottedDouble.setDecimalSeparator('.');
        formatter.setDecimalFormatSymbols(dottedDouble);
// Разместить надпись внутри панели
        panel.add(label);
// Установить выравнивание надписи по левому краю панели
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));

            panel.setBackground(Color.WHITE);
            panel.setForeground(Color.BLACK);

    }

    public Component getTableCellRendererComponent(JTable table,
                                                   Object value, boolean isSelected, boolean hasFocus, int row, int col) {
// Преобразовать double в строку с помощью форматировщика


        String formattedDouble = formatter.format(value);
// Установить текст надписи равным строковому представлению числа
    if ((row + col) % 2 == 0) {
        panel.setBackground(Color.WHITE);
        label.setForeground(Color.BLACK);
    } else {
        panel.setBackground(Color.BLACK);
        label.setForeground(Color.WHITE);
    }

    label.setText(formattedDouble);




        if (col==1 && needle!=null && needle.equals(formattedDouble)) {
// Номер столбца = 1 (т.е. второй столбец) + иголка не null
// (значит что-то ищем) +
// значение иголки совпадает со значением ячейки таблицы -
// окрасить задний фон панели в красный цвет

            panel.setBackground(Color.RED);
        }
        if(range != null)
        {
            String strArr[] = range.split(" ");
            Double numArr[] = new Double[strArr.length];
            for (int i = 0; i < strArr.length; i++) {
                numArr[i] = Double.parseDouble(strArr[i]);
            }
            if(numArr[0] <= (Double)value && (Double)value <= numArr[1])
            {
                panel.setBackground(Color.BLUE);
            }
        }

        return panel;

    }
    public void setNeedle(String needle) {
        this.needle = needle;
    }
    public void setRange(String range)
    {
        this.range = range;
    }
    public void rangeClear()
    {
        range = null;
    }
}
