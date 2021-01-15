package com.kodilla.battleships;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.BooleanPropertyBase;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.scene.AccessibleRole;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Skin;
import javafx.scene.control.skin.ButtonSkin;

public class GameButton extends Button {

    /***************************************************************************
     *                                                                         *
     * Constructors                                                            *
     *                                                                         *
     **************************************************************************/

    /**
     * Creates a button with an empty string for its label.
     */
    public GameButton() {
        initialize();
    }
    public String cellNumber;
    /**
     * Creates a button with the specified text as its label.
     *
     * @param text A text string for its label.
     */
    public GameButton(String text) {
        //super(text);
        setPrefHeight(20);
        setPrefWidth(25);
        this.cellNumber = text;
        initialize();
    }

    /**
     * Creates a button with the specified text and icon for its label.
     *
     * @param text A text string for its label.
     * @param graphic the icon for its label.
     */
    public GameButton(String text, Node graphic) {
        super(text, graphic);
        initialize();
    }

    private void initialize() {
        getStyleClass().setAll(DEFAULT_STYLE_CLASS);
        setAccessibleRole(AccessibleRole.BUTTON);
        setMnemonicParsing(true);     // enable mnemonic auto-parsing by default
    }

    /***************************************************************************
     *                                                                         *
     * Properties                                                              *
     *                                                                         *
     **************************************************************************/

    /**
     * A default Button is the button that receives
     * a keyboard VK_ENTER press, if no other node in the scene consumes it.
     */
    private BooleanProperty defaultButton;




    /**
     * A Cancel Button is the button that receives
     * a keyboard VK_ESC press, if no other node in the scene consumes it.
     */
    private BooleanProperty cancelButton;



    /***************************************************************************
     *                                                                         *
     * Methods                                                                 *
     *                                                                         *
     **************************************************************************/

    /** {@inheritDoc} */
    @Override public void fire() {
        if (!isDisabled()) {
            fireEvent(new ActionEvent());
        }
    }

    /** {@inheritDoc} */
    @Override protected Skin<?> createDefaultSkin() {
        return new ButtonSkin(this);
    }


    /***************************************************************************
     *                                                                         *
     * Stylesheet Handling                                                     *
     *                                                                         *
     **************************************************************************/

    /**
     * Initialize the style class to 'button'.
     *
     * This is the selector class from which CSS can be used to style
     * this control.
     */
    private static final String DEFAULT_STYLE_CLASS = "button";

    private static final PseudoClass PSEUDO_CLASS_DEFAULT
            = PseudoClass.getPseudoClass("default");
    private static final PseudoClass PSEUDO_CLASS_CANCEL
            = PseudoClass.getPseudoClass("cancel");


    public int returnCellNumber(){
        try{
            return Integer.parseInt(cellNumber);
        }catch(Exception e){
            return -1;
        }

    }

    @Override
    public String toString() {
        return cellNumber;
    }
}
