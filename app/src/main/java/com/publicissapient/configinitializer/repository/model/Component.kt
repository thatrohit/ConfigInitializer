package com.publicissapient.configinitializer.repository.model

enum class Component {
    EDITTEXT, SWITCH, PICKER, UNDEFINED;

    companion object {
        fun getComponent(rawString: String?): Component =
            when (rawString) {
                "picker" -> PICKER
                "switch" -> SWITCH
                "text" -> EDITTEXT
                else -> UNDEFINED
            }
    }

}