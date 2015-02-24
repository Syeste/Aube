package aube.entities;


import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class CarteTiledMap {
	
	
	private TiledMap map;
	private MapLayers layersOfTheMap;
	private MapProperties propCarte;
	private int mapPixelWidth, mapPixelHeight;
	
	
	private TiledMapTileLayer oldLayerTopTiledMapTile,newLayerTopTopTiledMapTile,newLayerTopDownTiledMapTile,layerCollision;
//	private MapObjects layerMapTileObjet;
	private int[] layerBaseNumber = {0};
	private int[] layerTopDownNumber = {4};
	private int[] layerTopTopNumber = {5};
	
	
	public CarteTiledMap(Personnage P1){
		
		map = new TmxMapLoader().load("map/" +  P1.getMapNow() +".tmx");
		
		propCarte = map.getProperties();
		
		layersOfTheMap = map.getLayers();

		int mapWidth = propCarte.get("width", Integer.class);
		int mapHeight = propCarte.get("height", Integer.class);
		int tilePixelWidth = propCarte.get("tilewidth", Integer.class);
		int tilePixelHeight = propCarte.get("tileheight", Integer.class);

		mapPixelWidth = mapWidth * tilePixelWidth;
		mapPixelHeight = mapHeight * tilePixelHeight;	
		
		// STATIQUE !!!
		
		layerCollision = (TiledMapTileLayer)map.getLayers().get("TileCollision");
		oldLayerTopTiledMapTile = (TiledMapTileLayer)map.getLayers().get("TileTop");
		newLayerTopTopTiledMapTile = new TiledMapTileLayer(mapWidth,mapHeight,tilePixelWidth,tilePixelHeight);
		newLayerTopDownTiledMapTile = new TiledMapTileLayer(mapWidth,mapHeight,tilePixelWidth,tilePixelHeight);
		
		layersOfTheMap.add(newLayerTopDownTiledMapTile);
		layersOfTheMap.add(newLayerTopTopTiledMapTile);

	}
	
	public int[] getNumberBasicLayout(){
		return layerBaseNumber;
	}
	
	public int[] getNumberTopDownLayout(){
		return layerTopDownNumber;
	}	
	
	public int[] getNumberTopTopLayout(){
		return layerTopTopNumber;
	}
	
	
	public Boolean getCollisionObjet(Personnage P1){
		
		int x = (int)P1.getX();
		int y = (int)P1.getY();
		int height = P1.getHitbotHauteurSkinPixel();
		int width = P1.getHitbotLargeurSkinPixel();
		int estimateX,estimateY,estimateXTop,estimateYTop;
		estimateX = x/propCarte.get("tilewidth", Integer.class);
		estimateY = y/propCarte.get("tileheight", Integer.class);
		estimateXTop = (x+width)/propCarte.get("tilewidth", Integer.class);
		estimateYTop = (y+height)/propCarte.get("tileheight", Integer.class);
		

		for(MapObject listeObjet: map.getLayers().get("TileObjetTeleport").getObjects()){
			
			RectangleMapObject rectangleCol = (RectangleMapObject)listeObjet;
			
			int posXObjet = (int)rectangleCol.getRectangle().getX();
			int posYObjet = (int)rectangleCol.getRectangle().getY();

			posYObjet /= propCarte.get("tileheight", Integer.class);
			posXObjet /= propCarte.get("tilewidth", Integer.class);
			
//			int posXObjetTop = posXObjet + propCarte.get("tilewidth", Integer.class);
//			int posYObjetTop = posYObjet + propCarte.get("tileheight", Integer.class);
					
			for(int p=estimateX;p <= estimateXTop;p++){
				if(p == posXObjet && estimateY == posYObjet){
					P1.setMap((String) listeObjet.getProperties().get("Destination"));
					P1.setX(Float.parseFloat((String)listeObjet.getProperties().get("PositionX")));
					P1.setY(Float.parseFloat((String)listeObjet.getProperties().get("PositionY")));
					return true;
				}

				if(p == posXObjet && estimateYTop == posYObjet){
					P1.setMap((String) listeObjet.getProperties().get("Destination"));
					P1.setX(Float.parseFloat((String)listeObjet.getProperties().get("PositionX")));
					P1.setY(Float.parseFloat((String)listeObjet.getProperties().get("PositionY")));
					return true;
				}
			}
			
			for(int p=estimateY;p <= estimateYTop;p++){
				if(p == posYObjet && estimateX == posXObjet){
					P1.setMap((String) listeObjet.getProperties().get("Destination"));
					P1.setX(Float.parseFloat((String)listeObjet.getProperties().get("PositionX")));
					P1.setY(Float.parseFloat((String)listeObjet.getProperties().get("PositionY")));
					return true;
				}
				if(p == posYObjet && estimateXTop == posXObjet){
					P1.setMap((String) listeObjet.getProperties().get("Destination"));
					P1.setX(Float.parseFloat((String)listeObjet.getProperties().get("PositionX")));
					P1.setY(Float.parseFloat((String)listeObjet.getProperties().get("PositionY")));
					return true;
				}
			}
			
		}
		return false;
	}
	
	
	public boolean getCollision(int x,int y, int height, int width){
		
		int estimateX,estimateY,estimateXTop,estimateYTop;
		estimateX = x/propCarte.get("tilewidth", Integer.class);
		estimateY = y/propCarte.get("tileheight", Integer.class);
		estimateXTop = (x+width)/propCarte.get("tilewidth", Integer.class);
		estimateYTop = (y+height)/propCarte.get("tileheight", Integer.class);
		
		
		for(int p=estimateX;p <= estimateXTop;p++){
			if(layerCollision.getCell(p, estimateY)!= null)
				return true;
			if(layerCollision.getCell(p, estimateYTop)!= null)
				return true;
		}
		
		for(int p=estimateY;p <= estimateYTop;p++){
			if(layerCollision.getCell(estimateX, p)!= null)
				return true;
			if(layerCollision.getCell(estimateXTop, p)!= null)
				return true;
		}

		return false;
	}
	
	
	public boolean getTabTopToRender(Personnage p1){
		
		int estimateX,estimateY,estimateXTop,estimateYTop;
		boolean drawAll = false;
		
		estimateX = (int)p1.getX()/propCarte.get("tilewidth", Integer.class);
		estimateY = (int)p1.getY()/propCarte.get("tileheight", Integer.class);
		estimateXTop = ((int)p1.getX()+p1.getLargeurSkinPixel())/propCarte.get("tilewidth", Integer.class);
		estimateYTop = ((int)p1.getY()+p1.getHauteurSkinPixel())/propCarte.get("tileheight", Integer.class);
		
		
		
		for(int y = 0;y < oldLayerTopTiledMapTile.getWidth();y++)
		{
			for(int x = 0; x < oldLayerTopTiledMapTile.getHeight();x++)
			{	
				newLayerTopTopTiledMapTile.setCell(x,y,null);
				newLayerTopDownTiledMapTile.setCell(x,y,null);
				if(x >= estimateX && x <= estimateXTop && y >= estimateY && y <= estimateYTop){
					if(y == estimateY)
					{
						if(oldLayerTopTiledMapTile.getCell(x, y)!=null && drawAll == false){
							drawAll = true;
						}
					}
					if(drawAll == true){
						newLayerTopTopTiledMapTile.setCell(x, y, oldLayerTopTiledMapTile.getCell(x, y));	
					}else{
						newLayerTopDownTiledMapTile.setCell(x, y, oldLayerTopTiledMapTile.getCell(x, y));			
					}
				}else{
					newLayerTopDownTiledMapTile.setCell(x, y, oldLayerTopTiledMapTile.getCell(x, y));	
				}
			}
		}
		return drawAll;
	}
	
	
	
	public TiledMap getTiledMap(){
		return map;
	}
	
	public int getWidthMapPixel(){
		return mapPixelWidth;
	}
	
	public int getHeightMapPixel(){
		return mapPixelHeight;
	}
		
	public void dispose(){
		map.dispose();
		
	}

}
