/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.testing.notes.util;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

/** Unit tests for the implementation of {@link ImageFileImpl}. */
@RunWith(RobolectricTestRunner.class)
public class ImageFileImplTest {
    private ImageFileImpl mFileHelper;

    @Before
    public void createImageFile() throws IOException {
        // Get a reference to the class under test
        mFileHelper = new ImageFileImpl();
    }

    @Test
    public void create_setsImageFile() throws IOException {
        // When file helper is asked to create a file
        mFileHelper.create("myImage", "png");

        // Then the created file is stored inside the image file.
        assertThat(mFileHelper.mImageFile.exists(), is(true));
    }

    @Test
    public void create_pathMatchesNameAndExtension() throws IOException {
        // When file helper is asked to create a file
        mFileHelper.create("myImage", ".png");

        assertThat(mFileHelper.getPath().contains("myImage"), is(true));
        assertThat(mFileHelper.getPath().endsWith(".png"), is(true));
    }

    @Test
    public void delete_imageFileIsDeleted() throws IOException {
        mFileHelper.create("myImage", ".png");
        File file = mFileHelper.mImageFile;

        // When file should be deleted
        mFileHelper.delete();

        // Then stored file is deleted
        assertThat(file.exists(), is(false));
        assertThat(mFileHelper.mImageFile, is(nullValue()));
    }
}
