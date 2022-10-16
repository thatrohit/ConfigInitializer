# format for config.json

## Root level keys

Key|Is Required|Description
-|-|-
titleResourceId|Yes|Specifiy the string resource id for the title of your launcher app.
target|Yes|Package name for your target application.
targetActivity|Yes|Your target application's launcher activity name.
components|Yes|An array of different UI elements the launcher can have.

`components` can be of the following type:
- `picker` will create a dropdown for selecting from a list of strings. The items array required for the dropdown must be defined inside an `items` key
- `switch` will create a switch for booleans. All the switches must be defined `items` key.
- `text` will create a text field. This can be used for passing any custom arguments. 

## component keys
Key|Is Required|Description
-|-|-
type|Yes|Component type defined above.
titleResourceId|Yes|Specifiy the string resource id for the title of your component.
descriptionResourceId|No|Specifiy the string resource for any text description you'd want for your component.
items|No|An array of json objects which will specify the required values for this component's children.

## items keys
Key|Is Required|Description
-|-|-
titleResourceId|Yes|Specifiy the string resource id for the title of your item.
descriptionResourceId|No|Specifiy the string resource for any text description you'd want for your item.
value|Yes|Specify the value this item is going to hold.
extra|No|Any extra argument this item can have. It's unused as of now.
