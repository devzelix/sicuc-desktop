package com.culturacarabobo.sicuc.desktop.ui;

import java.awt.Graphics;
import java.time.LocalDate;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.culturacarabobo.sicuc.desktop.models.CultorResponse;
import com.culturacarabobo.sicuc.desktop.utils.ColorPalette;
import com.culturacarabobo.sicuc.desktop.utils.CursorStyles;
import com.culturacarabobo.sicuc.desktop.utils.FontStyles;
import com.culturacarabobo.sicuc.desktop.utils.IdToNameResolver;
import com.culturacarabobo.sicuc.desktop.utils.ImageResources;
import com.formdev.flatlaf.FlatClientProperties;

public class CultorDialog extends JDialog {

    /**
     * Constructs a modal dialog displaying detailed information about a Cultor.
     * 
     * @param parent the parent frame for dialog positioning
     * @param cultor the CultorResponse object containing data to display
     */
    public CultorDialog(JFrame parent, CultorResponse cultor) {
        super();
        setModal(true);
        setLayout(null);
        setTitle("Detalles del Cultor");
        // Set multiple icon sizes for the dialog
        setIconImages(Arrays.asList(ImageResources.ICON_16, ImageResources.ICON_32, ImageResources.ICON_48,
                ImageResources.ICON_64, ImageResources.ICON_128, ImageResources.ICON_256));
        setSize(780, 640);
        setBackground(ColorPalette.PRIMARY_COLOR);
        setLocationRelativeTo(parent); // Center dialog relative to parent
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // User image based on gender
        JLabel userImage = new JLabel(
                cultor.getGender().equals("F") ? ImageResources.FEMALE_USER_ICON : ImageResources.MALE_USER_ICON);
        userImage.setBounds(17, 30, 116, 135);

        // Label for "Full Name" title
        JLabel fullNameLabel = new JLabel("Nombre Completo :");
        fullNameLabel.setForeground(ColorPalette.TEXT_COLOR);
        fullNameLabel.setFont(FontStyles.FONT_TITLE.deriveFont(18f));
        fullNameLabel.setBounds(153, 30, 160, 30);

        // Label showing the full name of the cultor
        JLabel fullName = new JLabel(cultor.getFirstName() + " " + cultor.getLastName());
        fullName.setForeground(ColorPalette.TEXT_COLOR);
        fullName.setFont(FontStyles.FONT_TEXT.deriveFont(19f));
        fullName.setBounds(318, 30, 435, 30);

        // Label for "Gender" title
        JLabel genderLabel = new JLabel("Género :");
        genderLabel.setForeground(ColorPalette.TEXT_COLOR);
        genderLabel.setFont(FontStyles.FONT_TITLE.deriveFont(18f));
        genderLabel.setBounds(153, 75, 70, 30);

        // Label displaying gender text based on value
        JLabel gender = new JLabel(cultor.getGender().equals("F") ? "Femenino" : "Masculino");
        gender.setForeground(ColorPalette.TEXT_COLOR);
        gender.setFont(FontStyles.FONT_TEXT.deriveFont(19f));
        gender.setBounds(228, 75, 525, 30);

        // Label for "ID Number" title
        JLabel idNumberLabel = new JLabel("Número de Cédula :");
        idNumberLabel.setForeground(ColorPalette.TEXT_COLOR);
        idNumberLabel.setFont(FontStyles.FONT_TITLE.deriveFont(18f));
        idNumberLabel.setBounds(153, 120, 160, 30);

        // Label showing the ID number of the cultor
        JLabel idNumber = new JLabel(cultor.getIdNumber());
        idNumber.setForeground(ColorPalette.TEXT_COLOR);
        idNumber.setFont(FontStyles.FONT_TEXT.deriveFont(19f));
        idNumber.setBounds(318, 120, 435, 30);

        // Label for "Birth Date" title
        JLabel birthDateLabel = new JLabel("Fecha de Nacimiento :");
        birthDateLabel.setForeground(ColorPalette.TEXT_COLOR);
        birthDateLabel.setFont(FontStyles.FONT_TITLE.deriveFont(18f));
        birthDateLabel.setBounds(153, 165, 180, 30);

        // Format and display the birth date
        LocalDate date = cultor.getBirthDate();
        JLabel birthDate = new JLabel(
                String.format("%02d", date.getDayOfMonth()) + "/" + String.format("%02d", date.getMonthValue()) + "/"
                        + date.getYear());
        birthDate.setForeground(ColorPalette.TEXT_COLOR);
        birthDate.setFont(FontStyles.FONT_TEXT.deriveFont(19f));
        birthDate.setBounds(338, 165, 415, 30);

        // Label for "Phone Number" title
        JLabel phoneNumberLabel = new JLabel("Número de Teléfono :");
        phoneNumberLabel.setForeground(ColorPalette.TEXT_COLOR);
        phoneNumberLabel.setFont(FontStyles.FONT_TITLE.deriveFont(18f));
        phoneNumberLabel.setBounds(153, 210, 175, 30);

        // Label showing the cultor's phone number
        JLabel phoneNumber = new JLabel(cultor.getPhoneNumber());
        phoneNumber.setForeground(ColorPalette.TEXT_COLOR);
        phoneNumber.setFont(FontStyles.FONT_TEXT.deriveFont(19f));
        phoneNumber.setBounds(333, 210, 420, 30);

        // Label for "Email" title
        JLabel emailLabel = new JLabel("Correo Electrónico :");
        emailLabel.setForeground(ColorPalette.TEXT_COLOR);
        emailLabel.setFont(FontStyles.FONT_TITLE.deriveFont(18f));
        emailLabel.setBounds(153, 255, 165, 30);

        // Label showing the cultor's email address
        JLabel email = new JLabel(cultor.getEmail());
        email.setForeground(ColorPalette.TEXT_COLOR);
        email.setFont(FontStyles.FONT_TEXT.deriveFont(19f));
        email.setBounds(323, 255, 430, 30);

        // Label for "Instagram Username" title
        JLabel instagramUserLabel = new JLabel("Usuario de Instagram :");
        instagramUserLabel.setForeground(ColorPalette.TEXT_COLOR);
        instagramUserLabel.setFont(FontStyles.FONT_TITLE.deriveFont(18f));
        instagramUserLabel.setBounds(153, 300, 185, 30);

        // Label showing Instagram username or "No posee" if null
        JLabel instagramUser = new JLabel(cultor.getInstagramUser() == null ? "No posee" : cultor.getInstagramUser());
        instagramUser.setForeground(ColorPalette.TEXT_COLOR);
        instagramUser.setFont(FontStyles.FONT_TEXT.deriveFont(19f));
        instagramUser.setBounds(343, 300, 410, 30);

        // Label for "Home Address" title
        JLabel homeAddressLabel = new JLabel("Dirección :");
        homeAddressLabel.setForeground(ColorPalette.TEXT_COLOR);
        homeAddressLabel.setFont(FontStyles.FONT_TITLE.deriveFont(18f));
        homeAddressLabel.setBounds(153, 345, 85, 30);

        // Resolve municipality and parish names by their IDs and display full address
        String municipality = IdToNameResolver.getMunicipality(cultor.getMunicipalityId());
        String parish = IdToNameResolver.getParish(cultor.getParishId());
        JLabel homeAddress = new JLabel("Mcpo. " + municipality + ", Pqa. " + parish + ", " + cultor.getHomeAddress());
        homeAddress.setForeground(ColorPalette.TEXT_COLOR);
        homeAddress.setFont(FontStyles.FONT_TEXT.deriveFont(19f));
        homeAddress.setBounds(243, 345, 510, 30);

        // Label for "Art Career" title
        JLabel artCareerLabel = new JLabel("Carrera Artística :");
        artCareerLabel.setForeground(ColorPalette.TEXT_COLOR);
        artCareerLabel.setFont(FontStyles.FONT_TITLE.deriveFont(18f));
        artCareerLabel.setBounds(153, 390, 140, 30);

        // Compose art career details: category, discipline, years of experience
        String artCategory = IdToNameResolver.getArtCategory(cultor.getArtCategoryId());
        String artDiscipline = IdToNameResolver.getArtDiscipline(cultor.getArtDisciplineId());
        int yearsOfExperience = cultor.getYearsOfExperience();
        JLabel artCareer = new JLabel(
                artCategory + ", " + artDiscipline + ", " + yearsOfExperience
                        + (yearsOfExperience > 1 ? " años de experiencia" : " años de experiencia"));
        artCareer.setForeground(ColorPalette.TEXT_COLOR);
        artCareer.setFont(FontStyles.FONT_TEXT.deriveFont(19f));
        artCareer.setBounds(298, 390, 455, 30);

        // Label for "Group" title
        JLabel groupLabel = new JLabel("Agrupación :");
        groupLabel.setForeground(ColorPalette.TEXT_COLOR);
        groupLabel.setFont(FontStyles.FONT_TITLE.deriveFont(18f));
        groupLabel.setBounds(153, 435, 100, 30);

        // Label showing group name or "No posee" if null
        JLabel group = new JLabel(cultor.getGroupName() == null ? "No posee" : cultor.getGroupName());
        group.setForeground(ColorPalette.TEXT_COLOR);
        group.setFont(FontStyles.FONT_TEXT.deriveFont(19f));
        group.setBounds(258, 435, 495, 30);

        // Label for "Disability" title
        JLabel disabilityLabel = new JLabel("Discapacidad :");
        disabilityLabel.setForeground(ColorPalette.TEXT_COLOR);
        disabilityLabel.setFont(FontStyles.FONT_TITLE.deriveFont(18f));
        disabilityLabel.setBounds(153, 480, 115, 30);

        // Label showing disability or "No posee" if null
        JLabel disability = new JLabel(cultor.getDisability() == null ? "No posee" : cultor.getDisability());
        disability.setForeground(ColorPalette.TEXT_COLOR);
        disability.setFont(FontStyles.FONT_TEXT.deriveFont(19f));
        disability.setBounds(273, 480, 480, 30);

        // Label for "Illness" title
        JLabel illnessLabel = new JLabel("Enfermedad :");
        illnessLabel.setForeground(ColorPalette.TEXT_COLOR);
        illnessLabel.setFont(FontStyles.FONT_TITLE.deriveFont(18f));
        illnessLabel.setBounds(153, 525, 110, 30);

        // Label showing illness or "No posee" if null
        JLabel illness = new JLabel(cultor.getIllness() == null ? "No posee" : cultor.getIllness());
        illness.setForeground(ColorPalette.TEXT_COLOR);
        illness.setFont(FontStyles.FONT_TEXT.deriveFont(19f));
        illness.setBounds(268, 525, 485, 30);

        // Close button setup with style, cursor and action to dispose the dialog
        JButton closeButton = new JButton("CERRAR");
        closeButton.setForeground(ColorPalette.PRIMARY_COLOR);
        closeButton.setFont(FontStyles.FONT_TITLE.deriveFont(16f));
        closeButton.setBackground(ColorPalette.TEXT_COLOR);
        closeButton.setBounds(13, 550, 130, 40);
        closeButton.putClientProperty(FlatClientProperties.STYLE, "arc: 40");
        closeButton.setCursor(CursorStyles.HAND_CURSOR);
        closeButton.addActionListener(e -> dispose());

        // Main panel with custom paint to draw horizontal separator lines
        JPanel panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(ColorPalette.TEXT_COLOR);
                g.drawLine(318, 60, 753, 60);
                g.drawLine(228, 105, 753, 105);
                g.drawLine(318, 150, 753, 150);
                g.drawLine(338, 195, 753, 195);
                g.drawLine(333, 240, 753, 240);
                g.drawLine(323, 285, 753, 285);
                g.drawLine(343, 330, 753, 330);
                g.drawLine(243, 375, 753, 375);
                g.drawLine(298, 420, 753, 420);
                g.drawLine(258, 465, 753, 465);
                g.drawLine(273, 510, 753, 510);
                g.drawLine(268, 555, 753, 555);
            }
        };
        panel.setLayout(null);
        panel.setBackground(ColorPalette.PRIMARY_COLOR);
        panel.setBounds(0, 0, 780, 640);

        // Add all components to the main panel
        panel.add(userImage);
        panel.add(fullNameLabel);
        panel.add(fullName);
        panel.add(genderLabel);
        panel.add(gender);
        panel.add(idNumberLabel);
        panel.add(idNumber);
        panel.add(birthDateLabel);
        panel.add(birthDate);
        panel.add(phoneNumberLabel);
        panel.add(phoneNumber);
        panel.add(emailLabel);
        panel.add(email);
        panel.add(instagramUserLabel);
        panel.add(instagramUser);
        panel.add(homeAddressLabel);
        panel.add(homeAddress);
        panel.add(artCareerLabel);
        panel.add(artCareer);
        panel.add(groupLabel);
        panel.add(group);
        panel.add(disabilityLabel);
        panel.add(disability);
        panel.add(illnessLabel);
        panel.add(illness);
        panel.add(closeButton);

        // Add the panel to the dialog
        add(panel);

    }

}
