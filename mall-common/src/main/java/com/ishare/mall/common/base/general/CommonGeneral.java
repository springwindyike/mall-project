package com.ishare.mall.common.base.general;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class CommonGeneral {

		 public static String loadJSON (String url) {
				try {
					StringBuilder json = new StringBuilder();
					URL urlObject = new URL(url);
					URLConnection uc = urlObject.openConnection();
					BufferedReader in = new BufferedReader(new InputStreamReader(
							uc.getInputStream()));
					String inputLine = null;
					while ((inputLine = in.readLine()) != null) {
						json.append(inputLine);
					}
					in.close();
					return json.toString();
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}
}
