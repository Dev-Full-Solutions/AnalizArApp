package com.dpozzo68.analizarapp.entidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NotificationTextList {
private List<NotificationText> notificationTexts;
private int index = 0;
    public NotificationTextList() {
        notificationTexts = new ArrayList<>();
        populate();
    }
    public void populate() {
        notificationTexts.add(new NotificationText("\uD83C\uDF33 Cuidemos el medioambiente", "Recordá apagar las luces que no utilices."));
        notificationTexts.add(new NotificationText("\uD83C\uDF21\uFE0F ¿Cómo usar el aire acondicionado?", "Un aire acondicionado consume alrededor de 1kWh. Mantené la temperatura en 24 ºC en verano y 20 ºC en invierno para consumir menos."));
        notificationTexts.add(new NotificationText("\uD83C\uDF21\uFE0F ¿Cuánto consume un calefactor?", "Los calefactores eléctricos son los electrodomésticos que más energía consumen."));
        notificationTexts.add(new NotificationText("❄\uFE0F Limpiá regularmente los filtros", "Cuando los filtros del aire acondicionado están sucios el equipo debe funcionar por más tiempo para obtener el mismo resultado que con los filtros limpios."));
        notificationTexts.add(new NotificationText("\uD83C\uDFE0 Aislá térmicamente tu hogar", "Los materiales que funcionan como aislante térmico (lana de vidrio, poliestireno expandido, celulosa y otros.) tienen como característica una alta resistencia a la transferencia de calor. Así, reducimos las pérdidas de calor en invierno y las ganancias en verano de la vivienda."));
        notificationTexts.add(new NotificationText("\uD83D\uDEB2 ¡Usá la bicicleta!", "Cuando haya que realizar viajes cortos en ciudad lo mejor es hacerlo caminando o en bicicleta o en transporte público. El vehículo privado supone casi el 37% del consumo energético en el transporte terrestre."));
    }
    public List<NotificationText> getNotificationTexts() {
        return notificationTexts;
    }
    public int getIndex() {
        return index;
    }
    public void advanceIndex() {
        index++;
    }
    public NotificationText getNotificationText() {
        Random random = new Random();
        return notificationTexts.get(random.nextInt(notificationTexts.size()-1));
    }
    public boolean isEmpty() {
        return notificationTexts.isEmpty();
    }
}
