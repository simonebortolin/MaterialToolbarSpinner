# MaterialToolbarSpinner
[![](https://jitpack.io/v/simonebortolin/MaterialToolbarSpinner.svg)](https://jitpack.io/#simonebortolin/MaterialToolbarSpinner)

Android 3rd party library to make a ToolbarSpinner in a easy mode. With compatibility with pre-lollipop devices
Small wrapper on Spinner, which let you easily add it to the Toolbar to look in material-style and equally on different Android versions

## Screenshots
<a href="https://github.com/simonebortolin/MaterialToolbarSpinner/blob/master/image_1.png"><img src="https://github.com/simonebortolin/MaterialToolbarSpinner/blob/master/image_1.png" alt="" width="200px"></a>
<a href="https://github.com/simonebortolin/MaterialToolbarSpinner/blob/master/image_2.png"><img src="https://github.com/simonebortolin/MaterialToolbarSpinner/blob/master/image_2.png" alt="" width="200px"></a>
<a href="https://github.com/simonebortolin/MaterialToolbarSpinner/blob/master/image_3.png"><img src="https://github.com/simonebortolin/MaterialToolbarSpinner/blob/master/image_3.png" alt="" width="200px"></a>

## Installation

Step 1: Add this to your **root** build.gradle file (not your module build.gradle file):

    allprojects {
      repositories {
        ...
        maven { url "https://jitpack.io" }
      }
    }


Step 2: Add this to your module `build.gradle` file:

    dependencies {
      ...
        compile 'com.github.simonebortolin:MaterialToolbarSpinner:0.2'
    }

## How to use this library

It's needed to add `MaterialToolbarSpinner` view to the `Toolbar`:
```xml
<android.support.v7.widget.Toolbar
    android:id="@+id/toolbar"
    android:layout_width="match_parent"
    android:layout_height="?attr/actionBarSize"
    android:theme="@style/ThemeOverlay.SampleApp.Toolbar">

    <com.magorasystems.materialtoolbarspinner.MaterialToolbarSpinner
        android:id="@+id/materialToolbarSpinner"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"/>
</android.support.v7.widget.Toolbar>
```

And edit the styles.xml:
```xml
<resources>
    <style name="SpinExam" parent="Theme.AppCompat.Light.NoActionBar">
        ....

        <item name="toolbarStyle">@style/Widget.SpinExam.Toolbar</item>
        <item name="toolbarSpinnerStyle">@style/Widget.MTS.Spinner.Toolbar</item>
    </style>

    <style name="Widget.SpinExam.Toolbar" parent="Widget.AppCompat.Toolbar">
        <item name="android:background">?attr/colorPrimary</item>
    </style>

    <style name="ThemeOverlay.SpinExam.Toolbar" parent="ThemeOverlay.MTS.Toolbar">
        <item name="colorControlNormal">#fff</item>
    </style>
</resources>
```

Kotlin

    materialToolbarSpinner.adapter = object : MaterialToolbarSpinner.SimpleAdapter(this) {
        override fun getItem(position: Int): MaterialToolbarSpinner.Item  = MaterialToolbarSpinner.Item(list[position])
    
        override fun getCount() = list.size
    }
    
    materialToolbarSpinner.onClickListener = object : AdapterView.OnItemSelectedListener {
    
        override fun onItemSelected(adapterView: AdapterView<*>, view: View, position: Int, id: Long) {
            textView.text = list[position]
        }
    
        override fun onNothingSelected(adapterView: AdapterView<*>) {
            Toast.makeText(this@MainActivity, "No item selected", Toast.LENGTH_SHORT).show()
        }
    }
    
Java
    
    MaterialToolbarSpinner materialToolbarSpinner = findViewById(R.id.materialToolbarSpinner);
    materialToolbarSpinner.setAdapter(new MaterialToolbarSpinner.SimpleAdapter(this) {

        @Override
        public int getCount() {
            return list.length;
        }

        @NotNull
        @Override
        public MaterialToolbarSpinner.Item getItem(int position) {
            return new MaterialToolbarSpinner.Item(list[position]);
        }
    });

    materialToolbarSpinner.setOnClickListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            textView.text = list[position];
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            Toast.makeText(MainActivity.this, "No item selected", Toast.LENGTH_SHORT).show();
        }
    });

If you want more customization, you can directly implement the adapter from the MaterialToolbarSpinner.Adapter class.
  
## Credits


I thank all the authors of the various commits that I have included in my fork


## License

    Copyright (c) 2016 Magora Systems
    
    Permission is hereby granted, free of charge, to any person obtaining a copy 
    of this software and associated documentation files (the "Software"), to deal 
    in the Software without restriction, including without limitation the rights 
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell 
    copies of the Software, and to permit persons to whom the Software is 
    furnished to do so, subject to the following conditions:
    
    The above copyright notice and this permission notice shall be included in all 
    copies or substantial portions of the Software.
    
    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, 
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE 
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER 
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE 
    SOFTWARE.
