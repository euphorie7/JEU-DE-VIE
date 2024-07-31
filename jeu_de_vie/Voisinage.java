public abstract class Voisinage {
    private  void clone(int[] from , int [] in)
    {
        if(from.length !=in.length)
        {
            System.out.println("les deux tableaux sont pas de meme taille");

        }
        else
        {
            for(int i =0;i<from.length;i++)
            {
                in[i]=from[i];
            }
        }
    }
    protected int [] v_d_exist(Cellule c)
    {
        int [] cord=c.getCord();
        int [] T_tailles=c.getTailles();

            if(cord[0]<T_tailles[0]-1)
            {
                int [] v_d=new int[cord.length];
                clone(cord,v_d);
                v_d[0]+=1;
                return v_d;

            }
            else
            {
                return null;
            }


    }
    protected int [] v_down_exist(Cellule c)
    {
        int [] cord=c.getCord();
        int []tailles=c.getTailles();

            if(cord[1]<tailles[1]-1)
            {
                int [] v_down=new int[cord.length];
                clone(cord,v_down);
                v_down[1]+=1;
                return v_down;

            }
            else
            {
                return null;
            }

    }

    protected int [] v_g_exist(Cellule c)
    {
        int [] cord=c.getCord();


         if(cord[0]>0)
            {
                int [] v_g=new int[cord.length];
                clone(cord,v_g);
                v_g[0]-=1;
                return v_g;
            }
            else
            {
                return null;
            }

    }

    protected int [] v_up_exist(Cellule c)
    {
        int [] cord=c.getCord();


            if(cord[1]>0)
            {
                int [] v_up=new int[cord.length];
                clone(cord,v_up);
                v_up[1]-=1;
                return v_up;
            }
            else
            {
                return null;
            }

    }
    protected int [] v_gup_exist(Cellule c)
    {
        int [] cord=c.getCord();

         if(cord[0]>0 && cord[1]>0 )
            {
                int [] v_up=new int[cord.length];
                clone(cord,v_up);
                v_up[0]-=1;
                v_up[1]-=1;
                return v_up;
            }
            else
            {
                return null;
            }

    }
    protected int [] v_dup_exist(Cellule c)
    {
        int [] cord=c.getCord();
        int []tailles=c.getTailles();

            if(cord[0]<tailles[0]-1 && cord[1]>0 )
            {
                int [] v_up=new int[cord.length];
                clone(cord,v_up);
                v_up[0]+=1;
                v_up[1]-=1;
                return v_up;
            }
            else
            {
                return null;
            }

    }
    protected int [] v_gdown_exist(Cellule c)
    {
        int [] cord=c.getCord();
        int []tailles=c.getTailles();

            if(cord[0]>0 && cord[1]<tailles[1]-1 )
            {
                int [] v_up=new int[cord.length];
                clone(cord,v_up);
                v_up[0]-=1;
                v_up[1]+=1;
                return v_up;
            }
            else
            {
                return null;
            }

    }
    protected int [] v_ddown_exist(Cellule c)
    {
        int [] cord=c.getCord();
        int []tailles=c.getTailles();

            if(cord[0]<tailles[0]-1 && cord[1]<tailles[1]-1 )
            {
                int [] v_up=new int[cord.length];
                clone(cord,v_up);
                v_up[0]+=1;
                v_up[1]+=1;
                return v_up;
            }
            else
            {
                return null;
            }

    }

    

    protected int [] v_front_exist(Cellule c)
    {
        int [] cord=c.getCord();
        int []tailles=c.getTailles();
        if( tailles.length!=3)
        {
            System.out.println("ce n'est pas en dim 3");
        }
        else
        {
            if(cord[2]>0)
            {
                int [] v_d=new int[cord.length];
                clone(cord,v_d);
                v_d[2]-=1;
                return v_d;

            }
            else
            {
                return null;
            }
        }
        return null;
    }

    protected int [] v_back_exist(Cellule c)
    {
        int [] cord=c.getCord();
        int []tailles=c.getTailles();
        if( tailles.length!=3)
        {
            System.out.println("ce n'est pas en dim 3");
        }
        else
        {
            if(cord[2]<tailles[2]-1)
            {
                int [] v_d=new int[cord.length];
                clone(cord,v_d);
                v_d[2]+=1;
                return v_d;

            }
            else
            {
                return null;
            }
        }
        return null;
    }

    public abstract Cellule  [] voisinage(Cellule c);
}
