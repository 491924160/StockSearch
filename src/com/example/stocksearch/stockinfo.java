package com.example.stocksearch;

import java.util.ArrayList;

class stockinfo {
    public String CompanyName;
    public String CompanySymbol;
    public String Change;
    public String MarketCapitalization;
    public String LastTradePriceOnly;
    public String Open;
    public String Bid;
    public String DaysLow;
    public String DaysHigh;
    public String YearLow;
    public String YearHigh;
    public String PreviousClose;
    public String ChangeinPercent;
    public String Volume;
    public String Ask;
    public String AverageDailyVolume;
    public String OneyrTargetPrice;
    public String StockChartImageURL;
    public ArrayList<String> itemList;
    public ArrayList<String> linkList;

    public String resultTag="result";
    public String quoteTag="Quote";
    public String CompanyNameTag="Name";
    public String CompanySymbolTag="Symbol";
    public String ChangeTypeTag="ChangeType";
    public String ChangeTag="Change";
    public String MarketCapitalizationTag="MarketCapitalization";
    public String LastTradePriceOnlyTag="LastTradePriceOnly";
    public String OpenTag="Open";
    public String BidTag="Bid";
    public String DaysLowTag="DaysLow";
    public String DaysHighTag="DaysHigh";
    public String YearLowTag="YearLow";
    public String YearHighTag="YearHigh";
    public String PreviousCloseTag="PreviousClose";
    public String ChangeinPercentTag="ChangeInPercent";
    public String VolumeTag="Volume";
    public String AskTag="Ask";
    public String AverageDailyVolumeTag="AverageDailyVolume";
    public String OneyrTargetPriceTag="OneYearTargetPrice";
    public String StockChartImageURLTag = "StockChartImageURL";
    public String NewsTag="News";
    public String ItemTag="Item";
    public String LinkTag = "Link";
    public String TitleTag = "Title";

    public stockinfo(){
        itemList = new ArrayList<String>();
        linkList = new ArrayList<String>();
    }
}
