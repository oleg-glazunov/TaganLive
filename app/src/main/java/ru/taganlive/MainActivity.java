package ru.taganlive;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/*import com.appodeal.ads.Appodeal;*/
import com.squareup.picasso.Callback;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.sql.Time;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;

//import ru.taganlive.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String LOG_TAG = "myLogs";
    private ProgressBar progressBar;
    //Integer count = 1;
    boolean isBackPressed;

    //private SwipeRefreshLayout swipeNews;

    // новости
    public ListView listView_news;
    public LinearLayout linearLayout_content_news;
    ArrayList<HashMap<String, String>> arrayList_news;
    // контент выбранной новости
    public String url_content_news;
    public ImageView imageView_content_img_news;
    public String content_url_img_news;
    //public String http;
    public TextView textView_content_time_news;
    public String content_time_news;
    public TextView textView_content_news;

    // имена атрибутов для Map (Новости)
    final String ATTRIBUTE_TIME_NEWS = "time_news";
    final String ATTRIBUTE_TITLE_NEWS = "title_news";
    final String ATTRIBUTE_SUBTITLE_NEWS = "subtitle_news";
    final String ATTRIBUTE_URL_IMG_NEWS = "url_img_news";
    final String ATTRIBUTE_HREF_NEWS = "href_news";

    final int MENU_SIZE_16 = 1;
    final int MENU_SIZE_18 = 2;
    final int MENU_SIZE_20 = 3;

    // Кино NEO
    private LinearLayout linearLayout_kino;
    private LinearLayout linearLayout_content_kino;
    private ListView listView_kino;
    ArrayList<HashMap<String, String>> arrayList_kino;
    private TextView textView_date_kino;
    // контент кино
    private ImageView imageView_content_poster_kino;
    private String content_url_img_kino;
    private TextView textView_content_title_kino;
    private String content_title_kino;
    private TextView textView_content_title_kino_blue;
    private String content_title_kino_blue;
    private TextView textView_content_cof_kino;
    private String content_cof_kino;
    private TextView textView_content_genre_kino;
    private String content_genre_kino;
    private ImageView imageView_content_duration_kino;
    private String content_url_img_duration_kino;
    private TextView textView_content_duration_kino;
    private String content_duration_kino;
    private TextView textView_content_producer_kino;
    private String content_producer_kino;
    private TextView textView_content_cast_kino;
    private String content_cast_kino;
    private TextView textView_content_date_kino;
    private String content_date_kino;
    private TextView textView_content_description_kino;

    // имена атрибутов для Map (Чеховский)
    final String ATTRIBUTE_URL_IMG_KINO = "url_img_kino";
    final String ATTRIBUTE_HREF_KINO = "href_kino";
    final String ATTRIBUTE_DATE_KINO = "date_kino";
    final String ATTRIBUTE_TITLE_KINO = "title_kino";
    final String ATTRIBUTE_GENRE_KINO = "genre_kino";
    //сеансы в 4х кинотреатрах
    final String ATTRIBUTE_CINEMA1 = "cinema1";
    final String ATTRIBUTE_CINEMA2 = "cinema2";
    final String ATTRIBUTE_CINEMA3 = "cinema3";
    final String ATTRIBUTE_CINEMA4 = "cinema4";
    public String cinema1;
    public String cinema2;
    public String cinema3;
    public String cinema4;

    // Чеховский
    private LinearLayout linearLayout_teatr;
    private LinearLayout linearLayout_content_teatr;
    private ListView listView_teatr;
    ArrayList<HashMap<String, String>> arrayList_teatr;
    // контент театра
    private TextView textView_content_teatr;
    private ImageView imageView_content_teatr;
    private String content_url_img_teatr;
    // имена атрибутов для Map (Чеховский)
    final String ATTRIBUTE_TIME_TEATR = "time_teatr";
    final String ATTRIBUTE_HEADER_TEATR = "header_teatr";
    final String ATTRIBUTE_URL_IMG_TEATR = "url_img_teatr";
    final String ATTRIBUTE_HREF_TEATR = "href_teatr";

    // курсы валюты
    private LinearLayout linearLayout_rate;

    private TextView textView_yesterday;
    private TextView textView_today;

    private TextView textView_doll_yesterday;
    private TextView textView_euro_yesterday;

    private TextView textView_doll_today;
    private TextView textView_euro_today;

    private String yesterday;
    private String today;

    private String doll_symbol;
    private String euro_symbol;

    private String euro_yesterday;
    private String doll_yesterday;

    private String doll_today;
    private String euro_today;

    // драг. металы
    private TextView textView_metal_today;
    private TextView textView_metal_yesterday;

    private TextView textView_gold_yesterday;
    private TextView textView_silver_yesterday;
    private TextView textView_platinum_yesterday;
    private TextView textView_palladium_yesterday;

    private TextView textView_gold_today;
    private TextView textView_silver_today;
    private TextView textView_platinum_today;
    private TextView textView_palladium_today;

    private String metal_yesterday;
    private String gold_yesterday;
    private String silver_yesterday;
    private String platinum_yesterday;
    private String palladium_yesterday;

    private String metal_today;
    private String gold_today;
    private String silver_today;
    private String platinum_today;
    private String palladium_today;

    // текущая погода
    private LinearLayout linearLayout_weather;
    public ImageView imageView_weather;
    private TextView textView_loc;
    private TextView textView_dts;
    private TextView textView_dc;
    private TextView textView_tm;
    private TextView textView_pp;
    private TextView textView_hm;
    private TextView textView_ws;
    private TextView textView_cl;
    private TextView textView_sunrise;
    private TextView textView_sunset;
    //public String url_img_weather;
    public int imageWeatherResource;
    private String loc; //локация
    private String dts; //дата, время
    private String dc; //тип погоды
    private String tm; //температура
    private String pp; //вероятность осадков
    private String hm; //влажность
    private String ws; //ветер
    private String cl; //ветер
    private String sun_rise; //восход
    private String sun_set; //закат

    // погода на 5 дней
    // имена атрибутов для Map
    private ListView listView_weather;
    ArrayList<HashMap<String, String>> arrayList_weather;
    final String ATTRIBUTE_WEATHER_DATE = "weatherDate";
    final String ATTRIBUTE_TIME_FROM_TO = "timeFromTo";
    final String ATTRIBUTE_DAY_OF_WEEK = "dayOfWeek";
    final String ATTRIBUTE_WEATHER_ICO = "weatherIco";
    final String ATTRIBUTE_WEATHER_TEMP = "weatherTemp";
    final String ATTRIBUTE_WEATHER_TYPE = "weatherType";
    final String ATTRIBUTE_WIND_SPEED = "windSpeed";
    final String ATTRIBUTE_WIND_DIRECTION = "windDirection";
    final String ATTRIBUTE_ATMO_PRESSURE = "atmoPressure";
    final String ATTRIBUTE_HUMIDITY = "humidity";
    final String ATTRIBUTE_CLOUDS = "clouds";
    //public int imagesWeatherResource;
    private TextView textDayOfWeek;

    // settings
    private LinearLayout linearLayout_settings;
    String[] cities = {"Таганрог", "Ростов-на-Дону", "Шахты", "Новочеркасск", "Волгодонск"};
    String[] pages = {"3", "4", "5", "6", "7", "8", "9", "10"};
    public int position_cities;
    public int position_pages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Toolbar mToolbar = findViewById(R.id.toolbar);
        //toolbar.setSubtitle("Новости");
        setSupportActionBar(mToolbar);
        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Appodeal
        /*String appKey = "cbd3fe5ac992ad47a36293adc3821cbed6d4ff6b2e4467ca";
        Appodeal.initialize(this, appKey, Appodeal.BANNER | Appodeal.INTERSTITIAL);
        Appodeal.show(this, Appodeal.BANNER_BOTTOM);
        Appodeal.disableNetwork(this, "cheetah");*/

        //check connect
        /*try {
            CheckConnect checkConnect = new CheckConnect();
            checkConnect.execute();
        } catch (IOException e) {
            Toast.makeText(MainActivity.this, "Сервер новостей недоступен, попробуйте позже", Toast.LENGTH_LONG).show();
            Log.d(LOG_TAG, "Сервер новостей недоступен", e);
        }*/

        progressBar = findViewById(R.id.progressBar);
        //progressBar.setMax(10);

        //swipeNews = (SwipeRefreshLayout) findViewById(R.id.swipe_news);

        // Settings
        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        ArrayAdapter<String> adapter_cities = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cities);
        ArrayAdapter<String> adapter_pages = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, pages);
        // Определяем разметку для использования при выборе элемента
        adapter_cities.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter_pages.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        Spinner spinner_cities = findViewById(R.id.cities);
        spinner_cities.setAdapter(adapter_cities);
        Spinner spinner_pages = findViewById(R.id.pages);
        spinner_pages.setAdapter(adapter_pages);
        // заголовок
        spinner_cities.setPrompt("Title");
        spinner_pages.setPrompt("Title");
        // выделяем элемент
        spinner_cities.setSelection(0);
        spinner_pages.setSelection(0);

        // устанавливаем обработчик нажатия
        spinner_cities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position1, long id) {
                // показываем позиция нажатого элемента
                position_cities = position1;
                //Toast.makeText(getBaseContext(), "Cities pos = " + position_cities, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        spinner_pages.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // показываем позиция нажатого элемента
                position_pages = position;
                //Toast.makeText(getBaseContext(), "Pages pos = " + position_pages, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        // кнопка обновления новостей
        Button btnUpdate = findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseTitleBloknotTaganrog parseTitleBloknotTaganrog = new ParseTitleBloknotTaganrog();
                try {
                    arrayList_news = new ArrayList<>();
                    arrayList_news = parseTitleBloknotTaganrog.execute().get();
                    ListAdapter sAdapter =
                            new MySimpleAdapterNews(
                                    MainActivity.this,
                                    arrayList_news,
                                    R.layout.list_news2,
                                    new String[]{ATTRIBUTE_TIME_NEWS, ATTRIBUTE_TITLE_NEWS, ATTRIBUTE_SUBTITLE_NEWS},
                                    new int[]{R.id.time_news, R.id.header_news, R.id.subtitle_news});
                    // определяем список и присваиваем ему адаптер
                    listView_news = findViewById(R.id.listView_news);
                    listView_news.setAdapter(sAdapter);
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getBaseContext(), "Загружено", Toast.LENGTH_SHORT).show();
            }
        });

        // меню размера шрифта
        textView_content_news = findViewById(R.id.content_news);
        registerForContextMenu(textView_content_news);

        //новости
        //TextView header_news = (TextView) findViewById(R.id.header);
        //TextView date_news = (TextView) findViewById(R.id.date);

        linearLayout_content_news = findViewById(R.id.linearLayout_contentNews);

        //Кино NEO
        listView_kino = findViewById(R.id.listView_kino);
        linearLayout_kino = findViewById(R.id.linearLayout_kino);
        linearLayout_content_kino = findViewById(R.id.linearLayout_contentKino);
        //textView_date_kino = findViewById(R.id.textView_date_kino);

        //Чехоский
        listView_teatr = findViewById(R.id.listView_teatr);
        linearLayout_teatr = findViewById(R.id.linearLayout_teatr);
        linearLayout_content_teatr = findViewById(R.id.linearLayout_contentTeatr);

        //курсы валюты
        linearLayout_rate = findViewById(R.id.linearLayout_rate);

        textView_yesterday = findViewById(R.id.textView_yesterday);
        textView_doll_yesterday = findViewById(R.id.textView_doll_yesterday);
        textView_euro_yesterday = findViewById(R.id.textView_euro_yesterday);

        textView_today = findViewById(R.id.textView_today);
        textView_doll_today = findViewById(R.id.textView_doll_today);
        textView_euro_today = findViewById(R.id.textView_euro_today);

        //драг. металы
        textView_metal_yesterday = findViewById(R.id.textView_metal_yesterday);
        textView_metal_today = findViewById(R.id.textView_metal_today);

        textView_gold_yesterday = findViewById(R.id.textView_gold_yesterday);
        textView_silver_yesterday = findViewById(R.id.textView_silver_yesterday);
        textView_platinum_yesterday = findViewById(R.id.textView_platinum_yesterday);
        textView_palladium_yesterday = findViewById(R.id.textView_palladium_yesterday);

        textView_gold_today = findViewById(R.id.textView_gold_today);
        textView_silver_today = findViewById(R.id.textView_silver_today);
        textView_platinum_today = findViewById(R.id.textView_platinum_today);
        textView_palladium_today = findViewById(R.id.textView_palladium_today);

        // текущая погода
        linearLayout_weather = findViewById(R.id.linearLayout_weather);
        textView_loc = findViewById(R.id.textView_loc);
        textView_dts = findViewById(R.id.textView_dts);
        textView_dc = findViewById(R.id.textView_dc);
        textView_tm = findViewById(R.id.textView_tm);
        textView_pp = findViewById(R.id.textView_pp);
        textView_hm = findViewById(R.id.textView_hm);
        textView_ws = findViewById(R.id.textView_ws);
        textView_cl = findViewById(R.id.textView_cl);
        textView_sunrise = findViewById(R.id.textView_sunrise);
        textView_sunset = findViewById(R.id.textView_sunset);
        // прогноз погоды на 5 дней
        listView_weather = findViewById(R.id.listView_weather);
        // выходные в красный цвет
        //textColorRed = ContextCompat.getColor(this, R.color.ColorRed);
        textDayOfWeek = findViewById(R.id.dayOfWeek);

        // settings
        linearLayout_settings = findViewById(R.id.linearLayout_settings);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectionSpecs(Arrays.asList(ConnectionSpec.MODERN_TLS, ConnectionSpec.COMPATIBLE_TLS))
                .build();

//        OkHttpClient client = new OkHttpClient.Builder()
//                .sslSocketFactory(socketFactory())
//                .build();

        final Picasso mPicasso = new Picasso.Builder(MainActivity.this)
                .downloader(new OkHttp3Downloader(client))
                .build();

        ParseTitleBloknotTaganrog parseTitleBloknotTaganrog = new ParseTitleBloknotTaganrog();
        try {

            //count = 1;
            //progressBar.setVisibility(View.VISIBLE);
            //progressBar.setProgress(0);

            arrayList_news = new ArrayList<>();
            arrayList_news = parseTitleBloknotTaganrog.execute().get();

            // создаем адаптер
            ListAdapter sAdapter =
                    new MySimpleAdapterNews(
                            MainActivity.this,
                            arrayList_news,
                            R.layout.list_news2,
                            new String[]{ATTRIBUTE_TIME_NEWS, ATTRIBUTE_TITLE_NEWS, ATTRIBUTE_SUBTITLE_NEWS},
                            new int[]{R.id.time_news, R.id.header_news, R.id.subtitle_news});

            // определяем список и присваиваем ему адаптер
            listView_news = findViewById(R.id.listView_news);
            listView_news.setAdapter(sAdapter);

            listView_news.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ParseContentBloknotTaganrog parseContentMyTaganrog = new ParseContentBloknotTaganrog();
                    parseContentMyTaganrog.execute(arrayList_news.get(position).get(ATTRIBUTE_HREF_NEWS)); // получаем url выбранной новости
                    try {
                        listView_news.setVisibility(View.GONE);
                        linearLayout_content_news.setVisibility(View.VISIBLE);
                        linearLayout_kino.setVisibility(View.GONE);
                        linearLayout_content_kino.setVisibility(View.GONE);
                        linearLayout_teatr.setVisibility(View.GONE);
                        linearLayout_content_teatr.setVisibility(View.GONE);
                        linearLayout_rate.setVisibility(View.GONE);
                        linearLayout_weather.setVisibility(View.GONE);
                        linearLayout_settings.setVisibility(View.GONE);

                        // вставляем новость
                        textView_content_news = findViewById(R.id.content_news);
                        textView_content_news.setText(parseContentMyTaganrog.get());
                        //textView_content_news.setTextIsSelectable(true);

                        isBackPressed = false;
                        supportInvalidateOptionsMenu(); //Перестроить меню

                        url_content_news = arrayList_news.get(position).get(ATTRIBUTE_HREF_NEWS);

                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }

                    // вставляем фото новости из url
                    imageView_content_img_news = findViewById(R.id.img_content_news);
                    imageView_content_img_news.requestFocus(); // возвращаем фокус

                    //Ion.with(MainActivity.this).load(content_url_img_news).intoImageView(imageView_content_img_news);

                    //Picasso.with(MainActivity.this).load(content_url_img_news).into(imageView_content_img_news); // old
                    //Picasso.get().load(content_url_img_news).into(imageView_content_img_news);
                    Picasso.get()
                            .load(content_url_img_news)
                            .into(imageView_content_img_news, new Callback() {
                                @Override
                                public void onSuccess() {
                                }

                                @Override
                                public void onError(Exception e) {
                                }
                            });

//                    mPicasso
//                            //.load(content_url_img_news)
//                            .load("https://datarush.ru/weather/logo_bloknot.jpg")
//                            .into(imageView_content_img_news, new Callback() {
//                                @Override
//                                public void onSuccess() {
//                                }
//                                @Override
//                                public void onError(Exception e) {
//                                }
//                            });

                    //img_content_news.setAdjustViewBounds(true);

                    // вставляем дату новости
                    textView_content_time_news = findViewById(R.id.content_time_news);
                    textView_content_time_news.setText(content_time_news);

                }
            });
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        ParseRate parseRate = new ParseRate();
        parseRate.execute();
        ParseWeather parseWeather = new ParseWeather();
        parseWeather.execute();

//        Thread thread = new Thread(new Runnable() {
//            public void run() {
//
//            }
//        });
//        thread.start();

        /*swipeNews.setColorSchemeResources(android.R.color.holo_blue_dark, android.R.color.holo_blue_light, android.R.color.holo_green_light, android.R.color.holo_green_dark);
        swipeNews.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // TODO Auto-generated method stub
                swipeNews.setRefreshing(true);
                Log.d("Swipe", "Refreshing News");
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        swipeNews.setRefreshing(false);

                        ParseTitleBloknotTaganrog parseTitleBloknotTaganrog = new ParseTitleBloknotTaganrog();
                        try {
                            progressBar.setVisibility(View.VISIBLE);
                            arrayList_news = new ArrayList<>();
                            arrayList_news = parseTitleBloknotTaganrog.execute().get();

                            ListAdapter sAdapter =
                                    new MySimpleAdapterNews(
                                            MainActivity.this,
                                            arrayList_news,
                                            R.layout.list_news2,
                                            new String[]{ATTRIBUTE_TIME_NEWS, ATTRIBUTE_TITLE_NEWS, ATTRIBUTE_SUBTITLE_NEWS},
                                            new int[]{R.id.time_news, R.id.header_news, R.id.subtitle_news});

                            // определяем список и присваиваем ему адаптер
                            listView_news = (ListView) findViewById(R.id.listView);
                            listView_news.setAdapter(sAdapter);

                        } catch (InterruptedException | ExecutionException e) {
                            e.printStackTrace();
                        }
                    }
                }, 0);
            }
        });*/

        /*final SwipeRefreshLayout swipeRate = (SwipeRefreshLayout) findViewById(R.id.swipe_rate);
        swipeRate.setColorSchemeResources(android.R.color.holo_blue_dark, android.R.color.holo_blue_light, android.R.color.holo_green_light, android.R.color.holo_green_dark);
        swipeRate.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // TODO Auto-generated method stub
                swipeRate.setRefreshing(true);
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        swipeRate.setRefreshing(false);
                        ParseRate parseRate = new ParseRate();
                        parseRate.execute();
                    }
                }, 3000);
            }
        });*/
    }


    // SSL
    private SSLSocketFactory socketFactory() {
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            @SuppressLint("TrustAllX509TrustManager")
            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }

            @SuppressLint("TrustAllX509TrustManager")
            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        }};
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            return sslContext.getSocketFactory();
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            throw new RuntimeException("Failed to create a SSL socket factory", e);
        }
    }


    // парсинг TitleBloknotTaganrog
    @SuppressLint("StaticFieldLeak")
    private class ParseTitleBloknotTaganrog extends AsyncTask<String, Integer, ArrayList<HashMap<String, String>>> {
        @Override
        protected void onPreExecute() {
            // обновляем пользовательский интерфейс сразу после выполнения задачи
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
        }

        @Override
        protected ArrayList<HashMap<String, String>> doInBackground(String... strings) {

            ArrayList<HashMap<String, String>> arrayListNews = new ArrayList<>();

            String url_news = "";
            String url_news_tag = "https://donday-taganrog.ru";
            String url_news_ros = "https://donday.ru";
            String url_news_sha = "https://donday-shakhty.ru";
            String url_news_nov = "https://donday-novocherkassk.ru";
            String url_news_vol = "https://donday-volgodonsk.ru";

            switch (position_cities) {
                case 0:
                    url_news = url_news_tag;
                    break;
                case 1:
                    url_news = url_news_ros;
                    break;
                case 2:
                    url_news = url_news_sha;
                    break;
                case 3:
                    url_news = url_news_nov;
                    break;
                case 4:
                    url_news = url_news_vol;
                    break;
            }

            try {
                for (int i = 1; i <= position_pages + 3; i++) {

                    Document doc = Jsoup.connect(url_news + "/page/" + i)
                            .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.0.0 Safari/537.36")
                            .sslSocketFactory(socketFactory())
                            .referrer("https://www.google.com/")
                            .timeout(5000)
                            .get();

                    Elements elements = doc.select(".shortstory");

                    for (Element element : elements) {

                        // заголовок
                        Element title = element.select(".btl").first();

                        // дата
                        Element date_time = element.select(".argbox").first();

                        // категория
                        Element cat = element.select(".argcat").first();

                        // начало статьи
                        Element article = element.select("a").select("span").get(0);

                        // фото
                        Element img = element.select("picture").select("source[srcset~=(?i)\\.(png|gif|jpe?g|webp)]").first();

                        // ссылка
                        Element href = element.select("a[href]").first();

                        HashMap<String, String> map = new HashMap<>();
                        map.put(ATTRIBUTE_TIME_NEWS, cat.text() + ", " + date_time.text());
                        map.put(ATTRIBUTE_TITLE_NEWS, title.text());
                        map.put(ATTRIBUTE_SUBTITLE_NEWS, article.text());
                        map.put(ATTRIBUTE_URL_IMG_NEWS, url_news + img.attr("srcset"));
                        map.put(ATTRIBUTE_HREF_NEWS, href.attr("abs:href"));

                        // adding HashList to ArrayList
                        arrayListNews.add(map);
                    }
                }
            } catch (IOException e) {
                //e.printStackTrace();
                Log.d(LOG_TAG, "Ошибка парсинга TitleBloknotTaganrog", e);
            }
            return arrayListNews;
        }

        @Override
        protected void onPostExecute(ArrayList<HashMap<String, String>> hashMaps) {
            super.onPostExecute(hashMaps);
            progressBar.setVisibility(View.GONE);
        }
    }

    // парсинг ContentBloknotTaganrog
    @SuppressLint("StaticFieldLeak")
    private class ParseContentBloknotTaganrog extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {
            Document doc = null;
            String content = "";

            String url_news = "";
            String url_news_tag = "https://donday-taganrog.ru";
            String url_news_ros = "https://donday.ru";
            String url_news_sha = "https://donday-shakhty.ru";
            String url_news_nov = "https://donday-novocherkassk.ru";
            String url_news_vol = "https://donday-volgodonsk.ru";

            switch (position_cities) {
                case 0:
                    url_news = url_news_tag;
                    break;
                case 1:
                    url_news = url_news_ros;
                    break;
                case 2:
                    url_news = url_news_sha;
                    break;
                case 3:
                    url_news = url_news_nov;
                    break;
                case 4:
                    url_news = url_news_vol;
                    break;
            }

            try {
                doc = Jsoup
                        .connect(params[0])
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.0.0 Safari/537.36")
                        .sslSocketFactory(socketFactory())
                        .referrer("https://www.google.com/")
                        .timeout(5000)
                        .get();

                // дата
                Element date_time = doc.select(".argbox").first();
                content_time_news = date_time.text();

                // фото
                Element img = doc.select("picture").select("source[srcset~=(?i)\\.(png|gif|jpe?g|webp)]").first();
                content_url_img_news = url_news + img.attr("srcset");

                // текст
                Element text = doc.select(".maincont").first();
                text.select(".yandex-favorit").remove();
                text.select(".rate").remove();
                text.select(".basetags").remove();

                content = text.text() + "\n" + "Ссылка: " + params[0];

            } catch (IOException e) {
                Log.d(LOG_TAG, "Ошибка парсинга ContentBloknotTaganrog", e);
            }
            return content;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressBar.setVisibility(View.GONE);
        }
    }

    // My SimpleAdapter for view image
    private class MySimpleAdapterNews extends SimpleAdapter {

        private MySimpleAdapterNews(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
            super(context, data, resource, from, to);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            // here you let SimpleAdapter built the view normally.
            View v = super.getView(position, convertView, parent);

            // Then we get reference for Picasso
            ImageView img = (ImageView) v.getTag();
            if (img == null) {
                img = v.findViewById(R.id.img_news);
                v.setTag(img); // <<< THIS LINE !!!!
            }
            // get the url from the data you passed to the `Map`
            String url = (String) ((Map) getItem(position)).get(ATTRIBUTE_URL_IMG_NEWS);
            // do Picasso
            //Picasso.with(v.getContext()).load(url).into(img);
            Picasso.get().load(url).into(img);
            //Picasso.get().load("https://datarush.ru/weather/logo_bloknot.jpg").into(img);

            // return the view
            return v;
        }
    }

    //парсинг TitleKino
    @SuppressLint("StaticFieldLeak")
    private class ParseTitleKino extends AsyncTask<String, Void, ArrayList<HashMap<String, String>>> {
        @Override
        protected void onPreExecute() {
            //what you want to do before AsyncTask execution(runs on UI thread)
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onProgressUpdate(Void[] values) {
            //About updates..........
        }

        @Override
        protected ArrayList<HashMap<String, String>> doInBackground(String... strings) {
            ArrayList<HashMap<String, String>> arrayListKino = new ArrayList<>();

            Document doc = null;

            try {
                doc = Jsoup
                        .connect("https://kinoneo.ru/schedule")
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.110 Safari/537.36")
                        .ignoreContentType(true)
                        .timeout(5000)
                        .get();

                // дата
                Element date_kino_ = doc.select("#data-holder").first();
                String date_kino = date_kino_.text();

                Elements elements = doc.select(".schedule-row");
                for (Element element : elements) {

                    // фото
                    Element img = element.select("img").first();

                    // ссылка
                    Element href = element.select("a[href]").first();

                    // заголовок
                    //Element title_kino = element.select("span[class=blue title]").first();
                    Element title_kino = element.select(".title-row").select("span").get(0);

                    // жанр
                    Element genre_kino = element.select(".title-row").select("span").get(1);

                    // время сеансов
                    Elements schedules = element.select(".schedule-all").first().getElementsByTag("tr");
                    int count = 0;
                    cinema1 = "";
                    cinema2 = "";
                    cinema3 = "";
                    cinema4 = "";
                    for (Element schedule : schedules) {
                        count++;
                        if (count == 1) {
                            Element cinema_1 = schedule.getElementsByTag("tr").first();
                            cinema1 = cinema_1.text();
                        }
                        if (count == 2) {
                            Element cinema_2 = schedule.getElementsByTag("tr").first();
                            cinema2 = cinema_2.text();
                        }
                        if (count == 3) {
                            Element cinema_3 = schedule.getElementsByTag("tr").first();
                            cinema3 = cinema_3.text();
                        }
                        if (count == 4) {
                            Element cinema_4 = schedule.getElementsByTag("tr").first();
                            cinema4 = cinema_4.text();
                        }
                    }

                    //System.out.println(title_kino.text() + " | " + genre_kino.text() + " | " + cinema1 + " | " + cinema2 + " | " + cinema3 + " | " + cinema4);

                    HashMap<String, String> map = new HashMap<>();
                    map.put(ATTRIBUTE_URL_IMG_KINO, img.attr("abs:src"));
                    map.put(ATTRIBUTE_HREF_KINO, href.attr("abs:href"));
                    map.put(ATTRIBUTE_DATE_KINO, "Кино HEO, " + date_kino);
                    map.put(ATTRIBUTE_TITLE_KINO, title_kino.text() + " (" + genre_kino.text() + ")");
                    map.put(ATTRIBUTE_GENRE_KINO, "(" + genre_kino.text() + ")");
                    map.put(ATTRIBUTE_CINEMA1, cinema1);
                    map.put(ATTRIBUTE_CINEMA2, cinema2);
                    map.put(ATTRIBUTE_CINEMA3, cinema3);
                    map.put(ATTRIBUTE_CINEMA4, cinema4);

                    // adding HashList to ArrayList
                    arrayListKino.add(map);
                }

            } catch (Exception e) {
                Log.d(LOG_TAG, "Ошибка парсинга TitleKino", e);
            }
            return arrayListKino;
        }

        @Override
        protected void onPostExecute(ArrayList<HashMap<String, String>> hashMaps) {
            super.onPostExecute(hashMaps);
            progressBar.setVisibility(View.GONE);
        }
    }

    //парсинг ContentKino
    @SuppressLint("StaticFieldLeak")
    private class ParseContentKino extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {
            Document doc = null;
            String content_description_kino = "";
            try {
                doc = Jsoup
                        .connect(params[0])
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.110 Safari/537.36")
                        .timeout(5000)
                        .get();

                // заголовок
                Element content_title_kino_ = doc.select(".pull-left > h3").first();
                content_title_kino = content_title_kino_.text();
                // заголовок blue
                Element content_title_kino_blue_ = doc.select(".pull-left > p").first();
                content_title_kino_blue = content_title_kino_blue_.ownText();
                // постер
                Element img = doc.select(".trailer").select("img").first();
                content_url_img_kino = "https://kinoneo.ru" + img.attr("src");

                // год+страна
                Element content_cof_kino_ = doc.select(".pull-left > p.cof").first();
                content_cof_kino = content_cof_kino_.text();

                // жанр
                Element content_genre_kino_ = doc.select(".pull-left").select("span").first();
                content_genre_kino = content_genre_kino_.text();

                // продолжительность
                Element img2 = doc.select(".pull-right").select("img").first();
                content_url_img_duration_kino = "https://kinoneo.ru" + img2.attr("src");
                Element content_duration_kino_ = doc.select(".pull-right").select("span").first();
                content_duration_kino = content_duration_kino_.text();

                // режисер
                Element content_producer_kino_ = doc.select(".info-table").first().getElementsByTag("tr").get(0);
                content_producer_kino = content_producer_kino_.text();

                // в ролях
                Element content_cast_kino_ = doc.select(".info-table").first().getElementsByTag("tr").get(1);
                content_cast_kino = content_cast_kino_.text();

                // дата премьеры
                Element content_date_kino_ = doc.select(".row > p").get(1);
                content_date_kino = content_date_kino_.text();

                // описание
                Element content_description_kino_ = doc.select(".row > p").get(2);
                content_description_kino = content_description_kino_.text();

            } catch (IOException e) {
                Log.d(LOG_TAG, "Ошибка парсинга ContentBloknotTaganrog", e);
            }
            return content_description_kino;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressBar.setVisibility(View.GONE);
        }
    }

    // My SimpleAdapter for view image
    private class MySimpleAdapterKino extends SimpleAdapter {

        private MySimpleAdapterKino(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
            super(context, data, resource, from, to);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            // here you let SimpleAdapter built the view normally.
            View v = super.getView(position, convertView, parent);

            // Then we get reference for Picasso
            ImageView img = (ImageView) v.getTag();
            if (img == null) {
                img = v.findViewById(R.id.img_kino);
                v.setTag(img); // <<< THIS LINE !!!!
            }
            // get the url from the data you passed to the `Map`
            String url = (String) ((Map) getItem(position)).get(ATTRIBUTE_URL_IMG_KINO);
            // do Picasso
            //Picasso.with(v.getContext()).load(url).into(img);
            Picasso.get().load(url).into(img);

            // return the view
            return v;
        }
    }

    //парсинг TitleTeatr
    @SuppressLint("StaticFieldLeak")
    private class ParseTitleTeatr extends AsyncTask<String, Void, ArrayList<HashMap<String, String>>> {
        @Override
        protected void onPreExecute() {
            //what you want to do before AsyncTask execution(runs on UI thread)
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onProgressUpdate(Void[] values) {
            //About updates..........
        }

        @Override
        protected ArrayList<HashMap<String, String>> doInBackground(String... strings) {
            ArrayList<HashMap<String, String>> arrayListTeatr = new ArrayList<>();

            Document doc = null;
            Element href = null;

            try {
                doc = Jsoup
                        .connect("http://chehovsky.ru/afisha/afisha")
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.110 Safari/537.36")
                        .ignoreContentType(true)
                        .timeout(5000)
                        .get();

                Elements elements = doc.getElementsByTag("td");
                for (Element element : elements) {
                    if (element.text().length() > 12 && element.text().length() < 500) {

                        // дата спектакля
                        Element date = element.select("span[class=day_title]").first();

                        // название спектакля
                        Elements performances = element.select(".performance");
                        for (Element performance : performances) {
                            // ссылка на описаниие
                            href = performance.select("a[href]").first();
                        }

                        HashMap<String, String> map = new HashMap<>();

                        // фото спектакля
//                        Element img = element.select(".performance").select("img[src~=(?i)\\.(png|gif|jpe?g)]").first();
//                        if (img != null) {
//                            map.put(ATTRIBUTE_URL_IMG_TEATR, "http://chehovsky.ru" + img.attr("src"));
//                            //System.out.println("http://www.chehovsky.ru" + img.attr("src"));
//                        } else {
//                            map.put(ATTRIBUTE_URL_IMG_TEATR, null);
//                        }
                        Element img = element.select(".performance").select("img[src~=(?i)\\.(png|gif|jpe?g|webp)]").first();
                        if (img == null) {
                            map.put(ATTRIBUTE_URL_IMG_TEATR, "android.resource://" + getPackageName() + "/drawable/teatr_noimage");
                        } else {
                            map.put(ATTRIBUTE_URL_IMG_TEATR, "http://chehovsky.ru" + img.attr("src"));
                        }

                        //текущий год
                        java.util.Calendar calendar = java.util.Calendar.getInstance(java.util.TimeZone.getDefault(), java.util.Locale.getDefault());
                        calendar.setTime(new java.util.Date());
                        int currentYear = calendar.get(java.util.Calendar.YEAR);

                        map.put(ATTRIBUTE_TIME_TEATR, date.text() + " " + currentYear);
                        map.put(ATTRIBUTE_HEADER_TEATR, performances.text());
                        map.put(ATTRIBUTE_HREF_TEATR, href.attr("abs:href"));

                        // adding HashList to ArrayList
                        arrayListTeatr.add(map);

                        //hashMap.put(day.text() + " " + currentYear + ". " + performances.text(), href.attr("abs:href"));
                        //System.out.println(day.text() + " " + currentYear + ". " + performances.text() + " " + href.attr("abs:href"));

                    }
                }

            } catch (IOException e) {
                Log.d(LOG_TAG, "Ошибка парсинга TitleTeatr", e);
            }
            return arrayListTeatr;
        }

        @Override
        protected void onPostExecute(ArrayList<HashMap<String, String>> hashMaps) {
            super.onPostExecute(hashMaps);
            progressBar.setVisibility(View.GONE);
        }
    }

    //парсинг ContentTeatr
    @SuppressLint("StaticFieldLeak")
    private class ParseContentTeatr extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {

            Document doc = null;
            String str = "";
            content_url_img_teatr = "";

            try {
                doc = Jsoup
                        .connect(params[0])
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.110 Safari/537.36")
                        .ignoreContentType(true)
                        .timeout(5000)
                        .get();

                Element title = doc.select(".title").first();
                Element author = doc.select(".author").first();
                Element description = doc.select(".description").first();
                //Element persons = doc.select(".persons").get(0);

                Element image = doc.select(".lightbox").select("img").first();
                content_url_img_teatr = image.attr("abs:src");

                //System.out.println(title.text() + ". " + author.text() + ". " + description.text() + ". " + persons.text());
                //System.out.println(url_img_content_teatr);

                str = title.text() + ". " + author.text() + ". " + description.text(); // + ". " + persons.text();

            } catch (IOException e) {
                Log.d(LOG_TAG, "Ошибка парсинга ContentMyTaganrog", e);
            }
            return str;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressBar.setVisibility(View.GONE);
        }
    }

    // My SimpleAdapter for view image
    private class MySimpleAdapterTeatr extends SimpleAdapter {

        private MySimpleAdapterTeatr(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
            super(context, data, resource, from, to);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            // here you let SimpleAdapter built the view normally.
            View v = super.getView(position, convertView, parent);

            // Then we get reference for Picasso
            ImageView img = (ImageView) v.getTag();
            if (img == null) {
                img = v.findViewById(R.id.img_teatr);
                v.setTag(img); // <<< THIS LINE !!!!
            }
            // get the url from the data you passed to the `Map`
            String url = (String) ((Map) getItem(position)).get(ATTRIBUTE_URL_IMG_TEATR);
            // do Picasso
            //Picasso.with(v.getContext()).load(url).into(img);
            Picasso.get().load(url).into(img);

            // return the view
            return v;
        }
    }

    // курс валют
    @SuppressLint("StaticFieldLeak")
    private class ParseRate extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {
            String rate = "";
            String metal;
            String inflation = "";
            try {
                Document doc = Jsoup
                        .connect("https://cbr.ru/key-indicators/")
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.110 Safari/537.36")
                        .timeout(5000)
                        .get();

                // валюта
                Element rate_content = doc.select(".key-indicator_table_wrapper").get(1);
                yesterday = rate_content.select(".td-w-4").get(0).text();
                today = rate_content.select(".td-w-4").get(1).text();
                doll_symbol = "$";
                doll_yesterday = rate_content.select(".td-w-4").get(2).text();
                doll_today = rate_content.select(".td-w-4").get(3).text();
                euro_symbol = "€";
                euro_yesterday = rate_content.select(".td-w-4").get(4).text();
                euro_today = rate_content.select(".td-w-4").get(5).text();

                // драг.металы
                Element metal_content = doc.select(".key-indicator_table_wrapper").get(2);
                metal_yesterday = metal_content.select(".td-w-4").get(0).text();
                metal_today = metal_content.select(".td-w-4").get(1).text();

                gold_yesterday = "Золото Au   " + metal_content.select(".td-w-4").get(2).text();
                silver_yesterday = "Серебро Ag   " + metal_content.select(".td-w-4").get(4).text();
                platinum_yesterday = "Платина Pt   " + metal_content.select(".td-w-4").get(6).text();
                palladium_yesterday = "Палладий Pd   " + metal_content.select(".td-w-4").get(8).text();

                gold_today = "Золото Au   " + metal_content.select(".td-w-4").get(3).text();
                silver_today = "Серебро Ag   " + metal_content.select(".td-w-4").get(5).text();
                platinum_today = "Платина Pt   " + metal_content.select(".td-w-4").get(7).text();
                palladium_today = "Палладий Pd   " + metal_content.select(".td-w-4").get(9).text();

            } catch (IOException e) {
                Log.d(LOG_TAG, "Ошибка парсинга Rate", e);
            }
            return rate;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressBar.setVisibility(View.GONE);
        }
    }

    // погода
    @SuppressLint("StaticFieldLeak")
    private class ParseWeather extends AsyncTask<String, Void, ArrayList<HashMap<String, String>>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected ArrayList<HashMap<String, String>> doInBackground(String... strings) {
            ArrayList<HashMap<String, String>> arrayListWeather = new ArrayList<>();

            try {
                //String url = "https://api.openweathermap.org/data/2.5/weather?id=484907&mode=xml&units=metric&appid=4e18471d0de9bf62801d17fb78f2f75d&lang=ru";
                String url_weather = "https://datarush.ru/weather/weather.xml";
                Document doc = Jsoup.parse(new URL(url_weather).openStream(), "UTF-8", "", Parser.xmlParser());

                //текущая погода
                String sunrise = doc.select("sun").attr("rise"); //восход
                String sunset = doc.select("sun").attr("set"); //закат
                String temperature = doc.select("temperature").attr("value"); //температура
                String humidity = doc.select("humidity").attr("value"); //влажность
                String pressure = doc.select("pressure").attr("value"); //давление
                Element wind = doc.select("wind").first(); //ветер
                String windspeed = wind.select("speed").attr("value"); //скорость ветра
                String windspeedname = wind.select("speed").attr("name"); //скорость ветра (название)
                String direction = wind.select("direction").attr("value"); //направление
                String directioncode = wind.select("direction").attr("code"); //направление (название сокращенно)
                //String directionname = wind.select("direction").attr("name"); //направление (название)
                String clouds = doc.select("clouds").attr("value");  // облачность %
                //String cloudsname = doc.select("clouds").attr("name");  //облачность (название)
                String weathertype = doc.select("weather").attr("value"); // тип погоды
                String weatherico = doc.select("weather").attr("icon"); //иконка погоды
                String lastupdate = doc.select("lastupdate").attr("value"); //время обновления

                loc = "Погода в Таганроге";
                //dts = getCurrentTimeStamp();
                Date lastuptime = Time.valueOf(lastupdate.substring(11)); // переводим str в date
                Date newTime = new Date(lastuptime.getTime() + TimeUnit.HOURS.toMillis(3)); // + 3 часа
                @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss"); // переводим date в str
                dts = "Обновлено в " + dateFormat.format(newTime);

                // округляем скорость ветра до 1го знака после запятой
                float ws_ = Float.valueOf(windspeed); // str to float
                DecimalFormat df_ = new DecimalFormat("##.#"); // оставляем один знак после запятой
                windspeed = df_.format(ws_);
                windspeed = windspeed.replace(",", "."); // меняем запятую на точку

                // тип погоды с больщой буквы
                String dc_first = weathertype.substring(0, 1).toUpperCase();
                String dc_all = weathertype.substring(1);
                dc = dc_first + dc_all;

                switch (weatherico) {
                    case "01d":
                        weatherico = "ic_wi_day_sunny";
                        break; // clear sky Чистое небо
                    case "01n":
                        weatherico = "ic_wi_night_clear";
                        break;
                    case "02d":
                        weatherico = "ic_wi_day_cloudy";
                        break; // few clouds Малооблачно
                    case "02n":
                        weatherico = "ic_wi_night_cloudy";
                        break;
                    case "03d":
                        weatherico = "ic_wi_cloud";
                        break; // scattered clouds Рассеянные облака
                    case "03n":
                        weatherico = "ic_wi_cloud";
                        break;
                    case "04d":
                        weatherico = "ic_wi_cloudy";
                        break; // broken clouds Облачно
                    case "04n":
                        weatherico = "ic_wi_cloudy";
                        break; // overcast clouds Пасмурно
                    case "09d":
                        weatherico = "ic_wi_day_showers";
                        break; // shower rain Ливень
                    case "09n":
                        weatherico = "ic_wi_night_showers";
                        break;
                    case "10d":
                        weatherico = "ic_wi_day_rain";
                        break; // rain Дождь
                    case "10n":
                        weatherico = "ic_wi_night_rain";
                        break;
                    case "11d":
                        weatherico = "ic_wi_day_thunderstorm";
                        break; // thunderstorm Гроза
                    case "11n":
                        weatherico = "ic_wi_night_thunderstorm";
                        break;
                    case "13d":
                        weatherico = "ic_wi_day_snow";
                        break; // snow Снег
                    case "13n":
                        weatherico = "ic_wi_night_snow";
                        break;
                    case "50d":
                        weatherico = "ic_wi_fog";
                        break; // mist Туман
                    case "50n":
                        weatherico = "ic_wi_fog";
                        break;
                    default:
                        weatherico = "ic_wi_alien";
                        break;
                }

                //url_img_weather = "https://openweathermap.org/img/wn/"+weatherico+"@2x.png";
                String weather_uri = "@drawable/" + weatherico;
                imageWeatherResource = getResources().getIdentifier(weather_uri, null, getPackageName());

                switch (windspeedname.toLowerCase()) {
                    case "light breeze":
                        windspeedname = "Легкий ветер";
                        break;
                    case "gentle breeze":
                        windspeedname = "Нежный ветерок";
                        break;
                    case "moderate breeze":
                        windspeedname = "Умеренный ветер";
                        break;
                    case "fresh breeze":
                        windspeedname = "Свежий ветер";
                        break;
                    case "strong breeze":
                        windspeedname = "Сильный ветер";
                        break;
                    case "windy":
                        windspeedname = "Ветренно";
                        break;
                    case "calm":
                        windspeedname = "Спокойный ветер";
                        break;
                    default:
                        windspeedname = "Ветер";
                        break;
                }

                // конвертируем направление ветра на русский (Север-North, Юг-South, Запад-West, Восток-East)
                StringBuilder directionCodeRu = new StringBuilder();
                char[] codeArray = directioncode.toCharArray(); // Преобразуем строку directionCodeRu в массив символов (char)
                for (char value : codeArray) {
                    if (String.valueOf(value).equals("N")) {
                        directionCodeRu.append("С");
                    } else if (String.valueOf(value).equals("S")) {
                        directionCodeRu.append("Ю");
                    } else if (String.valueOf(value).equals("W")) {
                        directionCodeRu.append("З");
                    } else if (String.valueOf(value).equals("E")) {
                        directionCodeRu.append("В");
                    }
                }

                //tm = temperature.substring(0, temperature.length() - 1) + "°"; // удалим последний символ
                float t = Float.valueOf(temperature); // str to float
                DecimalFormat df = new DecimalFormat("##.#"); // оставляем один знак после запятой
                tm = df.format(t) + "°";
                tm = tm.replace(",", "."); // меняем запятую на точку
                ws = windspeedname + " " + windspeed + " м/с " + directionCodeRu; //  + " (" + direction + ")"
                cl = "Облачность " + clouds + " %";
                long p = Math.round(Float.valueOf(pressure) / 1.333); // str to float и округляем до целого
                pp = "Давление " + p + " мм рт.ст.";
                hm = "Влажность " + humidity + " %";
                //восход-закат
                Date oldSunrise = Time.valueOf(sunrise.substring(11)); // переводим str в date
                Date newSunrise = new Date(oldSunrise.getTime() + TimeUnit.HOURS.toMillis(3)); // + 3 часа
                @SuppressLint("SimpleDateFormat") DateFormat dateFormatSunrise = new SimpleDateFormat("HH:mm"); // переводим date в str
                Date oldSunset = Time.valueOf(sunset.substring(11)); // переводим str в date
                Date newSunset = new Date(oldSunset.getTime() + TimeUnit.HOURS.toMillis(3)); // + 3 часа
                @SuppressLint("SimpleDateFormat") DateFormat dateFormatSunset = new SimpleDateFormat("HH:mm"); // переводим date в str
                sun_rise = dateFormatSunrise.format(newSunrise).replaceFirst("^0", "");
                sun_set = dateFormatSunset.format(newSunset).replaceFirst("^0", "");

                // прогноз погоды на 5 дней
                //String url2 = "https://api.openweathermap.org/data/2.5/forecast?id=484907&mode=xml&units=metric&appid=4e18471d0de9bf62801d17fb78f2f75d&lang=ru";
                String url_forecast = "https://datarush.ru/weather/forecast.xml";
                Document doc2 = Jsoup.parse(new URL(url_forecast).openStream(), "UTF-8", "", Parser.xmlParser());
                Elements forecasts = doc2.select("time");
                for (Element forecast : forecasts) {

                    // дата и время с .. до ..
                    String weatherDate = forecast.attr("from").substring(0, 10); // дата
                    String weatherDateY = weatherDate.substring(0, 4);
                    String weatherDateM = weatherDate.substring(5, 7);
                    String weatherDateD = weatherDate.substring(8, 10);

                    // месяц словами
                    String weatherMonth = "...";
                    switch (weatherDateM) {
                        case "01":
                            weatherMonth = "января";
                            break;
                        case "02":
                            weatherMonth = "февраля";
                            break;
                        case "03":
                            weatherMonth = "марта";
                            break;
                        case "04":
                            weatherMonth = "апреля";
                            break;
                        case "05":
                            weatherMonth = "мая";
                            break;
                        case "06":
                            weatherMonth = "июня";
                            break;
                        case "07":
                            weatherMonth = "июля";
                            break;
                        case "08":
                            weatherMonth = "августа";
                            break;
                        case "09":
                            weatherMonth = "сентября";
                            break;
                        case "10":
                            weatherMonth = "октября";
                            break;
                        case "11":
                            weatherMonth = "ноября";
                            break;
                        case "12":
                            weatherMonth = "декабря";
                            break;
                    }

                    // собираем дату, удаляем 0 вначале
                    weatherDate = weatherDateD.replaceFirst("^0", "") + " " + weatherMonth; //  + "." + weatherDateY

                    // переводим дату str to date
                    DateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
                    Date date = format.parse(weatherDateD + "." + weatherDateM + "." + weatherDateY);

                    // день недели из даты
                    //String dateStr = new SimpleDateFormat("EEEE").format(date);
                    //System.out.println("dateStr = " + dateStr);

                    // номер дня недели
                    Calendar c = Calendar.getInstance();
                    assert date != null : "Date is null";
                    c.setTime(date);
                    int day_of_week = c.get(Calendar.DAY_OF_WEEK);

                    // день недели словами
                    String dayOfWeek = "...";
                    switch (day_of_week) {
                        case 1:
                            dayOfWeek = "Воскресенье";
                            break;
                        case 2:
                            dayOfWeek = "Понедельник";
                            break;
                        case 3:
                            dayOfWeek = "Вторник";
                            break;
                        case 4:
                            dayOfWeek = "Среда";
                            break;
                        case 5:
                            dayOfWeek = "Четверг";
                            break;
                        case 6:
                            dayOfWeek = "Пятница";
                            break;
                        case 7:
                            dayOfWeek = "Суббота";
                            break;
                    }

                    //if (dayOfWeek.equals("Суббота") || dayOfWeek.equals("Воскресенье")) {
                    //textDayOfWeek.setTextColor(Color.RED);
                    //}

                    // время
                    String timeFrom = forecast.attr("from").substring(11); // время с
                    timeFrom = timeFrom.substring(0, 2).replaceFirst("^0", ""); // убираем 0 вначале строки
                    String timeTo = forecast.attr("to").substring(11); // время до
                    timeTo = timeTo.substring(0, 2).replaceFirst("^0", ""); // убираем 0 вначале строки

                    // тип погоды с большой буквы
                    String weatherType = forecast.select("symbol").attr("name");
                    String weatherType_first = weatherType.substring(0, 1).toUpperCase();
                    String weatherType_all = weatherType.substring(1);
                    weatherType = weatherType_first + weatherType_all;

                    String weatherIco = forecast.select("symbol").attr("var"); //иконка погоды

                    //String windDirection2 = forecast.select("windDirection").attr("deg"); //направление ветра, градусы
                    String windDirection = forecast.select("windDirection").attr("code"); //направление ветра, сокращенно

                    // конвертируем направление ветра на русский (Север-North, Юг-South, Запад-West, Восток-East)
                    StringBuilder windDirectionRu = new StringBuilder();
                    char[] windDirectionArray = windDirection.toCharArray(); // Преобразуем строку directionCodeRu в массив символов (char)
                    for (char value : windDirectionArray) {
                        if (String.valueOf(value).equals("N")) {
                            windDirectionRu.append("С");
                        } else if (String.valueOf(value).equals("S")) {
                            windDirectionRu.append("Ю");
                        } else if (String.valueOf(value).equals("W")) {
                            windDirectionRu.append("З");
                        } else if (String.valueOf(value).equals("E")) {
                            windDirectionRu.append("В");
                        }
                    }

                    String windSpeed = forecast.select("windSpeed").attr("mps"); //скорость ветра
                    //String windSpeedName2 = forecast.select("windSpeed").attr("name"); // скорость ветра (название)
                    String weatherTemp = forecast.select("temperature").attr("value"); //температура
                    String atmoPressure = forecast.select("pressure").attr("value"); //давление
                    String humidity_ = forecast.select("humidity").attr("value"); //влажность в %
                    String clouds_ = forecast.select("clouds").attr("all");  //облачность в %

                    // округляем температуру до 1го знака после запятой
                    float tmp = Float.valueOf(weatherTemp); // str to float
                    DecimalFormat dft = new DecimalFormat("##.#"); // оставляем один знак после запятой
                    weatherTemp = dft.format(tmp) + "°";
                    weatherTemp = weatherTemp.replace(",", "."); // меняем запятую на точку

                    // округляем скорость ветра до 1го знака после запятой
                    float wS = Float.valueOf(windSpeed); // str to float
                    DecimalFormat df__ = new DecimalFormat("##.#"); // оставляем один знак после запятой
                    windSpeed = df__.format(wS);
                    windSpeed = windSpeed.replace(",", "."); // меняем запятую на точку

                    switch (weatherIco) {
                        case "01d":
                            weatherIco = "ic_wi_day_sunny";
                            break; // clear sky Чистое небо
                        case "01n":
                            weatherIco = "ic_wi_night_clear";
                            break;
                        case "02d":
                            weatherIco = "ic_wi_day_cloudy";
                            break; // few clouds Малооблачно
                        case "02n":
                            weatherIco = "ic_wi_night_cloudy";
                            break;
                        case "03d":
                            weatherIco = "ic_wi_cloud";
                            break; // scattered clouds Рассеянные облака
                        case "03n":
                            weatherIco = "ic_wi_cloud";
                            break;
                        case "04d":
                            weatherIco = "ic_wi_cloudy";
                            break; // broken clouds Облачно
                        case "04n":
                            weatherIco = "ic_wi_cloudy";
                            break; // overcast clouds Пасмурно
                        case "09d":
                            weatherIco = "ic_wi_day_showers";
                            break; // shower rain Ливень
                        case "09n":
                            weatherIco = "ic_wi_night_showers";
                            break;
                        case "10d":
                            weatherIco = "ic_wi_day_rain";
                            break; // rain Дождь
                        case "10n":
                            weatherIco = "ic_wi_night_rain";
                            break;
                        case "11d":
                            weatherIco = "ic_wi_day_thunderstorm";
                            break; // thunderstorm Гроза
                        case "11n":
                            weatherIco = "ic_wi_night_thunderstorm";
                            break;
                        case "13d":
                            weatherIco = "ic_wi_day_snow";
                            break; // snow Снег
                        case "13n":
                            weatherIco = "ic_wi_night_snow";
                            break;
                        case "50d":
                            weatherIco = "ic_wi_fog";
                            break; // mist Туман
                        case "50n":
                            weatherIco = "ic_wi_fog";
                            break;
                        default:
                            weatherIco = "ic_wi_alien";
                            break;
                    }

                    String weatherDrawIco = "@drawable/" + weatherIco;
                    int imageWeatherIco = getResources().getIdentifier(weatherDrawIco, null, getPackageName());

                    HashMap<String, String> map = new HashMap<>();
                    map.put(ATTRIBUTE_WEATHER_DATE, weatherDate);
                    map.put(ATTRIBUTE_TIME_FROM_TO, timeFrom + ":00 - " + timeTo + ":00");
                    map.put(ATTRIBUTE_DAY_OF_WEEK, dayOfWeek);
                    //map.put(ATTRIBUTE_WEATHER_ICO, "https://openweathermap.org/img/wn/"+weatherIco+"@2x.png");
                    map.put(ATTRIBUTE_WEATHER_ICO, String.valueOf(imageWeatherIco));
                    //map.put(ATTRIBUTE_WEATHER_ICO, String.valueOf(R.drawable.ic_wi_cloudy));
                    map.put(ATTRIBUTE_WEATHER_TEMP, weatherTemp);
                    map.put(ATTRIBUTE_WEATHER_TYPE, weatherType);
                    map.put(ATTRIBUTE_WIND_SPEED, "Ветер " + windSpeed + " м/с");
                    map.put(ATTRIBUTE_WIND_DIRECTION, windDirectionRu.toString());
                    map.put(ATTRIBUTE_ATMO_PRESSURE, "Давление " + Math.round(Float.valueOf(atmoPressure) / 1.333) + " мм рт.ст.");
                    map.put(ATTRIBUTE_HUMIDITY, "Влажность " + humidity_ + " %");
                    map.put(ATTRIBUTE_CLOUDS, "Облачность " + clouds_ + " %");
                    arrayListWeather.add(map);
                }

                // погода и курс в тулбаре
                Toolbar mToolbar = findViewById(R.id.toolbar);
                mToolbar.post(new Runnable() {
                    public void run() {
                        try {
                            getSupportActionBar().setSubtitle(doll_symbol + " " + doll_today.substring(0, 4) + " " + euro_symbol + " " + euro_today.substring(0, 4) + " | t " + tm);
                        } catch (NullPointerException e) {
                            getSupportActionBar().setSubtitle("");
                            Log.d(LOG_TAG, "Ошибка парсинга Rate", e);
                        }
                    }
                });

                //} catch (IOException | ParseException e) {
            } catch (Exception e) {
                //e.printStackTrace();
                System.out.println(e.getMessage());
                System.out.println("Ошибка парсинга Weather");
                Log.d(LOG_TAG, "Ошибка парсинга Weather", e);
            }
            return arrayListWeather;
        }

        @Override
        protected void onPostExecute(ArrayList<HashMap<String, String>> hashMaps) {
            super.onPostExecute(hashMaps);
            progressBar.setVisibility(View.GONE);
        }
    }

    // My SimpleAdapter for view image
    private class MySimpleAdapterWeather extends SimpleAdapter {

        private MySimpleAdapterWeather(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
            super(context, data, resource, from, to);
        }

//        public View getView(int position, View convertView, ViewGroup parent) {
//            // here you let SimpleAdapter built the view normally.
//            View v = super.getView(position, convertView, parent);
//
//            // Then we get reference for Picasso
//            ImageView img = (ImageView) v.getTag();
//            if (img == null) {
//                img = (ImageView) v.findViewById(R.id.weatherIco);
//                v.setTag(img); // <<< THIS LINE !!!!
//                //Drawable res = getResources().getDrawable(imagesWeatherResource);
//                //img.setImageDrawable(res);
//            }
//            // get the url from the data you passed to the `Map`
//            //String url = (String) ((Map) getItem(position)).get(ATTRIBUTE_WEATHER_ICO);
//            // do Picasso
//            //Picasso.with(v.getContext()).load(url).into(img);
//
//            // return the view
//            return v;
//        }
    }

    @SuppressLint("SimpleDateFormat")
    public String getCurrentTimeStamp() {
        return new SimpleDateFormat("dd.MM.yyyy HH:mm").format(new Date());
        //return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
    }

    //проверка подключения
    /*class CheckConnect extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            try {
                URL url = new URL("http://mytaganrog.com");
                URLConnection con = url.openConnection();
                con.connect();
                url_mytaganrog = String.valueOf(url);
                Log.d(LOG_TAG, "Есть подключение к " + url_mytaganrog);
            } catch (IOException e) {
                Log.d(LOG_TAG, "Сервер новостей недоступен", e);
            }
            return url_mytaganrog;
        }
    }*/

    /*void saveNightMode() {
        int currentNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        sPref = getPreferences(MODE_PRIVATE);
        Editor ed = sPref.edit();
        ed.putInt(saved_NightMode, currentNightMode);
        ed.commit();
    }

    void loadNightMode() {
        sPref = getPreferences(MODE_PRIVATE);
        value_NightMode = sPref.getInt(saved_NightMode, 0);
    }*/

    @Override
    public void onBackPressed() {
        if (linearLayout_content_news.getVisibility() == View.VISIBLE |
                linearLayout_kino.getVisibility() == View.VISIBLE |
                linearLayout_teatr.getVisibility() == View.VISIBLE |
                linearLayout_rate.getVisibility() == View.VISIBLE |
                linearLayout_weather.getVisibility() == View.VISIBLE) {

            listView_news.setVisibility(View.VISIBLE);
            linearLayout_content_news.setVisibility(View.GONE);
            linearLayout_kino.setVisibility(View.GONE);
            linearLayout_content_kino.setVisibility(View.GONE);
            linearLayout_teatr.setVisibility(View.GONE);
            linearLayout_content_teatr.setVisibility(View.GONE);
            linearLayout_rate.setVisibility(View.GONE);
            linearLayout_weather.setVisibility(View.GONE);
            linearLayout_settings.setVisibility(View.GONE);
            supportInvalidateOptionsMenu(); //Перестроить меню

        } else if (linearLayout_content_teatr.getVisibility() == View.VISIBLE) {

            listView_news.setVisibility(View.GONE);
            linearLayout_content_news.setVisibility(View.GONE);
            linearLayout_kino.setVisibility(View.GONE);
            linearLayout_content_kino.setVisibility(View.GONE);
            linearLayout_teatr.setVisibility(View.VISIBLE);
            linearLayout_content_teatr.setVisibility(View.GONE);
            linearLayout_rate.setVisibility(View.GONE);
            linearLayout_weather.setVisibility(View.GONE);
            linearLayout_settings.setVisibility(View.GONE);
            supportInvalidateOptionsMenu(); //Перестроить меню

        } else if (linearLayout_content_kino.getVisibility() == View.VISIBLE) {

            listView_news.setVisibility(View.GONE);
            linearLayout_content_news.setVisibility(View.GONE);
            linearLayout_kino.setVisibility(View.VISIBLE);
            linearLayout_content_kino.setVisibility(View.GONE);
            linearLayout_teatr.setVisibility(View.GONE);
            linearLayout_content_teatr.setVisibility(View.GONE);
            linearLayout_rate.setVisibility(View.GONE);
            linearLayout_weather.setVisibility(View.GONE);
            linearLayout_settings.setVisibility(View.GONE);
            supportInvalidateOptionsMenu(); //Перестроить меню

        } else {
            if (!isBackPressed) {
                isBackPressed = true;
                Toast.makeText(MainActivity.this, "Нажмите еще раз для выхода", Toast.LENGTH_SHORT).show();
            } else {
                //finish();
                System.exit(0);
            }
        }
    }

    /*@Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }*/

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        if (v.getId() == R.id.content_news) {
            menu.add(0, MENU_SIZE_16, 0, "16");
            menu.add(0, MENU_SIZE_18, 0, "18");
            menu.add(0, MENU_SIZE_20, 0, "20");
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_SIZE_16:
                textView_content_news.setTextSize(16);
                //textView_content_news.setText("Text size = 22");
                break;
            case MENU_SIZE_18:
                textView_content_news.setTextSize(18);
                //textView_content_news.setText("Text size = 26");
                break;
            case MENU_SIZE_20:
                textView_content_news.setTextSize(20);
                //textView_content_news.setText("Text size = 30");
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        MenuItem action_sync = menu.findItem(R.id.action_sync);
        MenuItem action_return = menu.findItem(R.id.action_return);
        MenuItem action_share_app = menu.findItem(R.id.action_share_app);
        MenuItem action_comment = menu.findItem(R.id.action_comment);
        MenuItem action_open_in_browser = menu.findItem(R.id.action_open_in_browser);
        MenuItem action_share_link = menu.findItem(R.id.action_share_link);

        if (listView_news.getVisibility() == View.VISIBLE) { //если список

            action_return.setVisible(false);
            action_open_in_browser.setVisible(false);
            action_share_link.setVisible(false);

        } else if (listView_news.getVisibility() == View.GONE) { //если контент

            action_sync.setVisible(false);
            action_return.setVisible(true);

            if (linearLayout_content_news.getVisibility() == View.VISIBLE) {
                action_share_app.setVisible(false);
                action_comment.setVisible(false);
                action_open_in_browser.setVisible(true);
                action_share_link.setVisible(true);
            }
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_sync:
                ParseTitleBloknotTaganrog parseTitleBloknotTaganrog = new ParseTitleBloknotTaganrog();
                try {
                    //progressBar.setVisibility(View.VISIBLE);
                    arrayList_news = new ArrayList<>();
                    arrayList_news = parseTitleBloknotTaganrog.execute().get();
                    ListAdapter sAdapter =
                            new MySimpleAdapterNews(
                                    MainActivity.this,
                                    arrayList_news,
                                    R.layout.list_news2,
                                    new String[]{ATTRIBUTE_TIME_NEWS, ATTRIBUTE_TITLE_NEWS, ATTRIBUTE_SUBTITLE_NEWS},
                                    new int[]{R.id.time_news, R.id.header_news, R.id.subtitle_news});
                    // определяем список и присваиваем ему адаптер
                    listView_news = findViewById(R.id.listView_news);
                    listView_news.setAdapter(sAdapter);
                    Toast.makeText(getBaseContext(), "Обновлено", Toast.LENGTH_SHORT).show();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.action_return:
                if (linearLayout_content_news.getVisibility() == View.VISIBLE |
                        linearLayout_kino.getVisibility() == View.VISIBLE |
                        linearLayout_teatr.getVisibility() == View.VISIBLE |
                        linearLayout_rate.getVisibility() == View.VISIBLE |
                        linearLayout_weather.getVisibility() == View.VISIBLE |
                        linearLayout_settings.getVisibility() == View.VISIBLE) {

                    listView_news.setVisibility(View.VISIBLE);
                    linearLayout_content_news.setVisibility(View.GONE);
                    linearLayout_kino.setVisibility(View.GONE);
                    linearLayout_content_kino.setVisibility(View.GONE);
                    linearLayout_teatr.setVisibility(View.GONE);
                    linearLayout_content_teatr.setVisibility(View.GONE);
                    linearLayout_rate.setVisibility(View.GONE);
                    linearLayout_weather.setVisibility(View.GONE);
                    linearLayout_settings.setVisibility(View.GONE);
                    supportInvalidateOptionsMenu(); //Перестроить меню
                } else if (linearLayout_content_teatr.getVisibility() == View.VISIBLE) {
                    listView_news.setVisibility(View.GONE);
                    linearLayout_content_news.setVisibility(View.GONE);
                    linearLayout_kino.setVisibility(View.GONE);
                    linearLayout_content_kino.setVisibility(View.GONE);
                    linearLayout_teatr.setVisibility(View.VISIBLE);
                    linearLayout_content_teatr.setVisibility(View.GONE);
                    linearLayout_rate.setVisibility(View.GONE);
                    linearLayout_weather.setVisibility(View.GONE);
                    linearLayout_settings.setVisibility(View.GONE);
                    supportInvalidateOptionsMenu(); //Перестроить меню
                } else if (linearLayout_content_kino.getVisibility() == View.VISIBLE) {
                    listView_news.setVisibility(View.GONE);
                    linearLayout_content_news.setVisibility(View.GONE);
                    linearLayout_kino.setVisibility(View.VISIBLE);
                    linearLayout_content_kino.setVisibility(View.GONE);
                    linearLayout_teatr.setVisibility(View.GONE);
                    linearLayout_content_teatr.setVisibility(View.GONE);
                    linearLayout_rate.setVisibility(View.GONE);
                    linearLayout_weather.setVisibility(View.GONE);
                    linearLayout_settings.setVisibility(View.GONE);
                    supportInvalidateOptionsMenu(); //Перестроить меню
                }
                break;
//            case R.id.action_settings:
//                Intent SecAct = new Intent(this, SettingsActivity.class);
//                startActivity(SecAct);
//                break;
            case R.id.action_open_in_browser:
                Intent openInBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse(url_content_news));
                startActivity(openInBrowser);
                break;
            case R.id.action_share_link:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, url_content_news);
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, "Поделиться новостью"));
                break;
            case R.id.action_share_app:
                Intent sendApp = new Intent();
                sendApp.setAction(Intent.ACTION_SEND);
                sendApp.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=ru.taganlive");
                sendApp.setType("text/plain");
                startActivity(Intent.createChooser(sendApp, "Поделиться приложением"));
                break;
            case R.id.action_comment:
                String appPackageName = getPackageName();
                Intent marketIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName));
                marketIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                startActivity(marketIntent);
                break;
            case R.id.action_exit:
                //finish();
                System.exit(0);
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint({"ResourceType", "SetTextI18n"})
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        /* Handle navigation view item clicks here. */
        int id = item.getItemId();

        if (id == R.id.nav_news) {

            listView_news.setVisibility(View.VISIBLE);
            linearLayout_content_news.setVisibility(View.GONE);
            linearLayout_kino.setVisibility(View.GONE);
            linearLayout_content_kino.setVisibility(View.GONE);
            linearLayout_teatr.setVisibility(View.GONE);
            linearLayout_content_teatr.setVisibility(View.GONE);
            linearLayout_rate.setVisibility(View.GONE);
            linearLayout_weather.setVisibility(View.GONE);
            linearLayout_settings.setVisibility(View.GONE);
            supportInvalidateOptionsMenu(); //Перестроить меню

        } else if (id == R.id.nav_kino) {
            //Toast.makeText(MainActivity.this, "В разработке", Toast.LENGTH_SHORT).show();

            listView_news.setVisibility(View.GONE);
            linearLayout_content_news.setVisibility(View.GONE);
            linearLayout_kino.setVisibility(View.VISIBLE);
            linearLayout_content_kino.setVisibility(View.GONE);
            linearLayout_teatr.setVisibility(View.GONE);
            linearLayout_content_teatr.setVisibility(View.GONE);
            linearLayout_rate.setVisibility(View.GONE);
            linearLayout_weather.setVisibility(View.GONE);
            linearLayout_settings.setVisibility(View.GONE);
            supportInvalidateOptionsMenu(); //Перестроить меню

            ParseTitleKino parseTitleKino = new ParseTitleKino();
            try {
                arrayList_kino = new ArrayList<>();
                arrayList_kino = parseTitleKino.execute().get();

                //textView_date_kino.setText(date_kino); //дата

                ListAdapter sAdapter =
                        new MySimpleAdapterKino(
                                MainActivity.this,
                                arrayList_kino,
                                R.layout.list_kino3,
                                new String[]{ATTRIBUTE_URL_IMG_KINO, ATTRIBUTE_DATE_KINO, ATTRIBUTE_TITLE_KINO, ATTRIBUTE_GENRE_KINO, ATTRIBUTE_CINEMA1, ATTRIBUTE_CINEMA2, ATTRIBUTE_CINEMA3, ATTRIBUTE_CINEMA4},
                                new int[]{R.id.img_kino, R.id.date_kino, R.id.title_kino, R.id.genre_kino, R.id.cinema1, R.id.cinema2, R.id.cinema3, R.id.cinema4});

                // определяем список и присваиваем ему адаптер
                listView_kino = findViewById(R.id.listView_kino);
                listView_kino.setAdapter(sAdapter);
                listView_kino.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ParseContentKino parseContentKino = new ParseContentKino();
                        parseContentKino.execute(arrayList_kino.get(position).get(ATTRIBUTE_HREF_KINO));
                        try {
                            listView_news.setVisibility(View.GONE);
                            linearLayout_content_news.setVisibility(View.GONE);
                            linearLayout_kino.setVisibility(View.GONE);
                            linearLayout_content_kino.setVisibility(View.VISIBLE);
                            linearLayout_teatr.setVisibility(View.GONE);
                            linearLayout_content_teatr.setVisibility(View.GONE);
                            linearLayout_rate.setVisibility(View.GONE);
                            linearLayout_weather.setVisibility(View.GONE);
                            linearLayout_settings.setVisibility(View.GONE);

                            textView_content_description_kino = findViewById(R.id.textView_content_description_kino);
                            textView_content_description_kino.setText(parseContentKino.get());

                            isBackPressed = false;

                            supportInvalidateOptionsMenu(); //Перестроить меню

                        } catch (InterruptedException | ExecutionException e) {
                            e.printStackTrace();
                        }

                        // вставляем название фильма
                        textView_content_title_kino = findViewById(R.id.textView_content_title_kino);
                        textView_content_title_kino.requestFocus(); // возвращаем фокус
                        textView_content_title_kino.setText(content_title_kino);
                        textView_content_title_kino_blue = findViewById(R.id.textView_content_title_kino_blue);
                        textView_content_title_kino_blue.setText(content_title_kino_blue);

                        // постер
                        imageView_content_poster_kino = findViewById(R.id.imageView_content_poster_kino);
                        //Ion.with(imageView_content_kino).load(content_url_img_kino);
                        //Picasso.with(MainActivity.this).load(content_url_img_kino).into(imageView_content_poster_kino);
                        Picasso.get().load(content_url_img_kino).into(imageView_content_poster_kino);
                        imageView_content_poster_kino.setAdjustViewBounds(true);

                        // год+страна
                        textView_content_cof_kino = findViewById(R.id.textView_content_cof_kino);
                        textView_content_cof_kino.setText(content_cof_kino);

                        // жанр
                        textView_content_genre_kino = findViewById(R.id.textView_content_genre_kino);
                        textView_content_genre_kino.setText(content_genre_kino);

                        // продолжительность
                        imageView_content_duration_kino = findViewById(R.id.imageView_content_duration_kino);
                        //Picasso.with(MainActivity.this).load(content_url_img_duration_kino).into(imageView_content_duration_kino);
                        Picasso.get().load(content_url_img_duration_kino).into(imageView_content_duration_kino);
                        textView_content_duration_kino = findViewById(R.id.textView_content_duration_kino);
                        textView_content_duration_kino.setText(content_duration_kino);

                        // режисер
                        textView_content_producer_kino = findViewById(R.id.textView_content_producer_kino);
                        textView_content_producer_kino.setText(content_producer_kino);

                        // в ролях
                        textView_content_cast_kino = findViewById(R.id.textView_content_cast_kino);
                        textView_content_cast_kino.setText(content_cast_kino);

                        // дата премьеры
                        textView_content_date_kino = findViewById(R.id.textView_content_date_kino);
                        textView_content_date_kino.setText(content_date_kino);

                    }
                });
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

        } else if (id == R.id.nav_teatr) {

            listView_news.setVisibility(View.GONE);
            linearLayout_content_news.setVisibility(View.GONE);
            linearLayout_kino.setVisibility(View.GONE);
            linearLayout_content_kino.setVisibility(View.GONE);
            linearLayout_teatr.setVisibility(View.VISIBLE);
            linearLayout_content_teatr.setVisibility(View.GONE);
            linearLayout_rate.setVisibility(View.GONE);
            linearLayout_weather.setVisibility(View.GONE);
            linearLayout_settings.setVisibility(View.GONE);
            supportInvalidateOptionsMenu(); //Перестроить меню

            ParseTitleTeatr parseTitleTeatr = new ParseTitleTeatr();
            try {
                arrayList_teatr = new ArrayList<>();
                arrayList_teatr = parseTitleTeatr.execute().get();

                ListAdapter sAdapter =
                        new MySimpleAdapterTeatr(
                                MainActivity.this,
                                arrayList_teatr,
                                R.layout.list_teatr2,
                                new String[]{ATTRIBUTE_TIME_TEATR, ATTRIBUTE_HEADER_TEATR},
                                new int[]{R.id.time_teatr, R.id.header_teatr});

                // определяем список и присваиваем ему адаптер
                listView_teatr = findViewById(R.id.listView_teatr);
                listView_teatr.setAdapter(sAdapter);

                listView_teatr.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ParseContentTeatr parseContentTeatr = new ParseContentTeatr();
                        parseContentTeatr.execute(arrayList_teatr.get(position).get(ATTRIBUTE_HREF_TEATR));
                        try {
                            listView_news.setVisibility(View.GONE);
                            linearLayout_content_news.setVisibility(View.GONE);
                            linearLayout_kino.setVisibility(View.GONE);
                            linearLayout_content_kino.setVisibility(View.GONE);
                            linearLayout_teatr.setVisibility(View.GONE);
                            linearLayout_content_teatr.setVisibility(View.VISIBLE);
                            linearLayout_rate.setVisibility(View.GONE);
                            linearLayout_weather.setVisibility(View.GONE);
                            linearLayout_settings.setVisibility(View.GONE);

                            //linearLayout_content_teatr.invalidate();

                            textView_content_teatr = findViewById(R.id.textView_content_teatr);
                            textView_content_teatr.setText(parseContentTeatr.get());

                            isBackPressed = false;
                            supportInvalidateOptionsMenu(); //Перестроить меню

                        } catch (InterruptedException | ExecutionException e) {
                            e.printStackTrace();
                        }

                        imageView_content_teatr = findViewById(R.id.imageView_content_teatr);
                        imageView_content_teatr.requestFocus(); // возвращаем фокус
                        //Ion.with(imageView_content_teatr).load(content_url_img_teatr);
                        //Picasso.with(MainActivity.this).load(content_url_img_teatr).into(imageView_content_teatr);
                        Picasso.get().load(content_url_img_teatr).into(imageView_content_teatr);
                        imageView_content_teatr.setAdjustViewBounds(true);

                    }
                });
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

        } else if (id == R.id.nav_rate) {
            listView_news.setVisibility(View.GONE);
            linearLayout_content_news.setVisibility(View.GONE);
            linearLayout_kino.setVisibility(View.GONE);
            linearLayout_content_kino.setVisibility(View.GONE);
            linearLayout_teatr.setVisibility(View.GONE);
            linearLayout_content_teatr.setVisibility(View.GONE);
            linearLayout_rate.setVisibility(View.VISIBLE);
            linearLayout_weather.setVisibility(View.GONE);
            linearLayout_settings.setVisibility(View.GONE);
            supportInvalidateOptionsMenu(); //Перестроить меню

            ParseRate parseRate = new ParseRate();
            parseRate.execute();

            //курсы валют
            textView_yesterday.setText(yesterday);
            textView_today.setText(today);
            textView_doll_yesterday.setText(doll_symbol + " " + doll_yesterday);
            textView_doll_today.setText(doll_symbol + " " + doll_today);
            textView_euro_yesterday.setText(euro_symbol + " " + euro_yesterday);
            textView_euro_today.setText(euro_symbol + " " + euro_today);

            //драг. металы
            textView_metal_yesterday.setText(metal_yesterday);
            textView_metal_today.setText(metal_today);

            textView_gold_yesterday.setText(gold_yesterday);
            textView_silver_yesterday.setText(silver_yesterday);
            textView_platinum_yesterday.setText(platinum_yesterday);
            textView_palladium_yesterday.setText(palladium_yesterday);

            textView_gold_today.setText(gold_today);
            textView_silver_today.setText(silver_today);
            textView_platinum_today.setText(platinum_today);
            textView_palladium_today.setText(palladium_today);

        } else if (id == R.id.nav_weather) {

            listView_news.setVisibility(View.GONE);
            linearLayout_content_news.setVisibility(View.GONE);
            linearLayout_kino.setVisibility(View.GONE);
            linearLayout_content_kino.setVisibility(View.GONE);
            linearLayout_teatr.setVisibility(View.GONE);
            linearLayout_content_teatr.setVisibility(View.GONE);
            linearLayout_rate.setVisibility(View.GONE);
            linearLayout_weather.setVisibility(View.VISIBLE);
            linearLayout_settings.setVisibility(View.GONE);
            supportInvalidateOptionsMenu(); //Перестроить меню

            ParseWeather parseWeather = new ParseWeather();
            try {
                arrayList_weather = new ArrayList<>();
                arrayList_weather = parseWeather.execute().get();

                //текущая погода
                imageView_weather = findViewById(R.id.imageView_weather);
                //Drawable res = getResources().getDrawable(imageWeatherResource); // не работает на Android 4.4
                //imageView_weather.setImageDrawable(res); // не работает на Android 4.4
                imageView_weather.setImageResource(imageWeatherResource);

                textView_loc.setText(loc);
                textView_dts.setText(dts);
                textView_dc.setText(dc);
                textView_tm.setText(tm);
                textView_pp.setText(pp);
                textView_hm.setText(hm);
                textView_ws.setText(ws);
                textView_cl.setText(cl);
                textView_sunrise.setText(sun_rise);
                textView_sunset.setText(sun_set);

                // на 5 дней
                ListAdapter sAdapter =
                        new MySimpleAdapterWeather(
                                MainActivity.this,
                                arrayList_weather,
                                R.layout.list_weather,
                                new String[]{ATTRIBUTE_WEATHER_DATE, ATTRIBUTE_TIME_FROM_TO,
                                        ATTRIBUTE_DAY_OF_WEEK, ATTRIBUTE_WEATHER_ICO,
                                        ATTRIBUTE_WEATHER_TEMP, ATTRIBUTE_WEATHER_TYPE,
                                        ATTRIBUTE_WIND_SPEED, ATTRIBUTE_WIND_DIRECTION,
                                        ATTRIBUTE_ATMO_PRESSURE, ATTRIBUTE_HUMIDITY,
                                        ATTRIBUTE_CLOUDS},
                                new int[]{R.id.weatherDate, R.id.timeFromTo, R.id.dayOfWeek,
                                        R.id.weatherIco, R.id.weatherTemp, R.id.weatherType,
                                        R.id.windSpeed, R.id.windDirection, R.id.atmoPressure,
                                        R.id.humidity, R.id.clouds});

                // определяем список и присваиваем ему адаптер
                listView_weather = findViewById(R.id.listView_weather);
                listView_weather.setAdapter(sAdapter);

            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

        /*} else if (id == R.id.nav_call_taxi) {
            Intent marketIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=ru.navigatortaxi.TaxiDroid&hl=ru"));
            startActivity(marketIntent);*/
//        } else if (id == R.id.nav_avtoradiotaganrog) {
//            Intent marketIntent = new Intent(Intent.ACTION_VIEW,
//                    Uri.parse("https://play.google.com/store/apps/details?id=ru.avtoradiotaganrog"));
//            startActivity(marketIntent);
        } else if (id == R.id.nav_settings) {
            listView_news.setVisibility(View.GONE);
            linearLayout_content_news.setVisibility(View.GONE);
            linearLayout_kino.setVisibility(View.GONE);
            linearLayout_content_kino.setVisibility(View.GONE);
            linearLayout_teatr.setVisibility(View.GONE);
            linearLayout_content_teatr.setVisibility(View.GONE);
            linearLayout_rate.setVisibility(View.GONE);
            linearLayout_weather.setVisibility(View.GONE);
            linearLayout_settings.setVisibility(View.VISIBLE);
            supportInvalidateOptionsMenu(); //Перестроить меню
        } else if (id == R.id.nav_exit) {
            System.exit(0);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
