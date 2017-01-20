/*
Copyright [2017] [Chutipon]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

import java.io.FileNotFoundException;

public class DecodeSubjectName {
    private String path = "";
    private String fullName = "";

    DecodeSubjectName(String levelName) {
        path += Index.DB_LOCATION;
        switch (levelName.charAt(0)) {
            case 'M':
                path += "MATH_DB";
                break;
            case 'P':
                path += "PHYSICS_DB";
                break;
            default:
                break;
        }
        path += "\\" + levelName.substring(0, levelName.indexOf('-')) + "\\";
        FileUtil tempFolder = new FileUtil(path);
        try {
            path += tempFolder.getMapFullNameFromFolder().get(levelName.substring(0, levelName.indexOf('0') - 1)) + "\\";
            fullName = path;
        } catch (FileNotFoundException ignored) {
        }
        try {
            FileUtil revGetter = new FileUtil(path);
            path += levelName + revGetter.getRev(levelName) + "\\" + levelName;
        } catch (Exception ignored) {
        }
    }

    @Override
    public String toString() {
        return path;
    }

    String getFullName() {
        return fullName;
    }

}
